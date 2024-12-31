package com.askrindo.transaction.service;

import com.askrindo.transaction.adaptor.feign.ParameterAdaptor;
import com.askrindo.transaction.exception.BadRequestException;
import com.askrindo.transaction.model.entity.MikroRumahku;
import com.askrindo.transaction.model.request.MikroRumahkuRequest;
import com.askrindo.transaction.model.response.ValidationResponse;
import com.askrindo.transaction.repository.MikroRumahKuRepository;
import com.askrindo.transaction.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class MikroRumahKuTransactionService {

    private final MikroRumahKuRepository mikroRumahKuRepository;
    private final ParameterAdaptor parameterAdaptor;

    @Transactional
    public ValidationResponse execute(MikroRumahkuRequest input, String kodeProduct) {
        log.info("MikroRumahku transaction started");
        if (!"MARKETING".equalsIgnoreCase(input.getRole())) throw new BadRequestException("Anda tidak memiliki akses");
        String newNumber;
        String lastNumber = mikroRumahKuRepository.getLastCertificate();
        if (ObjectUtils.isEmpty(lastNumber)) {
            newNumber = "00001";
        } else {
            newNumber = String.format("%05d", Integer.parseInt(lastNumber.split("/")[0]) + 1);
        }

        List<String> informasiKepemilikan = getLookUpKeys(Constants.LOOKUP_GORUP.ASMIK_INFO_KEPEMILIKAN.getValue());
        List<String> hubungan = getLookUpKeys(Constants.LOOKUP_GORUP.AHLI_WARIS.getValue());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate inputDate = LocalDate.parse(input.getJangkaWaktuAwal().toString(), dateTimeFormatter);
        LocalDate today = LocalDate.now();
        LocalDate minDate = today.minusDays(3);
        LocalDate maxDate = today.plusDays(3);
        LocalDate maxDateInYear = today.plusYears(1);

        MikroRumahku mikroRumahku = new MikroRumahku();
        mikroRumahku.setCreatedDate(LocalDateTime.now());
        mikroRumahku.setNamaTertanggung(input.getNamaTertanggung());
        mikroRumahku.setNomorKtp(ktpValidation(input));
        if (!emailValidation(input.getEmail()))
            throw new BadRequestException("Email tidak valid");
        mikroRumahku.setEmail(input.getEmail());
        mikroRumahku.setNomorTelepon(input.getNomorTelepon());
        setJangkaWaktuAwal(input, inputDate, minDate, maxDate, mikroRumahku, dateTimeFormatter);
        setJangkaWaktuAkhir(input, inputDate, maxDateInYear, mikroRumahku, dateTimeFormatter);
        if (informasiKepemilikan.contains(input.getInformasiKepemilikan())) {
            mikroRumahku.setInformasiKepemilikan(input.getInformasiKepemilikan());
        } else {
          throw new BadRequestException("Informasi kepemilikan tidak valid");
        }
        mikroRumahku.setAlamat(input.getAlamat());
        mikroRumahku.setNamaAhliWaris(input.getNamaAhliWaris());
        mikroRumahku.setTanggalLahirAhliWaris(LocalDate.parse(input.getTanggalLahirAhliWaris().toString(), dateTimeFormatter).atStartOfDay());
        mikroRumahku.setNomorTelepon(input.getNomorTelepon());
        if (hubungan.contains(input.getHubungan())) {
            mikroRumahku.setHubungan(input.getHubungan());
        } else {
            throw new BadRequestException("Hubungan tidak valid");
        }
        mikroRumahku.setJenisPaket(input.getJenisPaket());
            long jumlahHari = ChronoUnit.DAYS.between(
                    LocalDate.parse(input.getJangkaWaktuAwal().toString(), dateTimeFormatter),
                    LocalDate.parse(input.getJangkaWaktuAkhir().toString(), dateTimeFormatter)
            );
        mikroRumahku.setNilaiPremi(getCalculationOfPremi(input, jumlahHari));
        int year = LocalDateTime.now().getYear();
        String romanDay = toRoman(LocalDateTime.now().getDayOfMonth());
        mikroRumahku.setNomorSertifikat(newNumber+"/"+kodeProduct+"/"+romanDay+"/"+year);
        mikroRumahKuRepository.save(mikroRumahku);

        log.info("End of MikroRumahku transaction");
        return ValidationResponse.builder().result(Boolean.TRUE).build();
    }

    private static BigDecimal getCalculationOfPremi(MikroRumahkuRequest input, long jumlahHari) {
        BigDecimal totalPremi;
        if (input.getJenisPaket().equalsIgnoreCase(Constants.ASURANSI_MIKRO_RUMAHKU.SILVER.getCode())) {
            totalPremi = getTotalPremi(Constants.ASURANSI_MIKRO_RUMAHKU.SILVER, jumlahHari);
        } else if (input.getJenisPaket().equalsIgnoreCase(Constants.ASURANSI_MIKRO_RUMAHKU.GOLD.getCode())) {
            totalPremi = getTotalPremi(Constants.ASURANSI_MIKRO_RUMAHKU.GOLD, jumlahHari);
        } else {
            totalPremi = getTotalPremi(Constants.ASURANSI_MIKRO_RUMAHKU.PLATINUM, jumlahHari);
        }
        return totalPremi;
    }

    private static BigDecimal getTotalPremi(Constants.ASURANSI_MIKRO_RUMAHKU constantValue, long jumlahHari) {
        return BigDecimal.valueOf(Integer.parseInt(constantValue.getValue()))
                .multiply(BigDecimal.valueOf(jumlahHari)
                        .divide(BigDecimal.valueOf(365), RoundingMode.HALF_UP));
    }

    private static void setJangkaWaktuAkhir(MikroRumahkuRequest input, LocalDate inputDate, LocalDate maxDateInYear, MikroRumahku mikroRumahku, DateTimeFormatter dateTimeFormatter) {
        if (!inputDate.isAfter(maxDateInYear)) {
            mikroRumahku.setJangkaWaktuAkhir(LocalDate.parse(input.getJangkaWaktuAkhir().toString(), dateTimeFormatter).atStartOfDay());
        } else {
            throw new BadRequestException("Tanggal tidak valid");
        }
    }

    private static void setJangkaWaktuAwal(MikroRumahkuRequest input, LocalDate inputDate, LocalDate minDate, LocalDate maxDate, MikroRumahku mikroRumahku, DateTimeFormatter dateTimeFormatter) {
        if (!inputDate.isBefore(minDate) && !inputDate.isAfter(maxDate)) {
            mikroRumahku.setJangkaWaktuAwal(LocalDate.parse(input.getJangkaWaktuAwal().toString(), dateTimeFormatter).atStartOfDay());
        } else {
            throw new BadRequestException("Tanggal tidak valid");
        }
    }

    private static String ktpValidation(MikroRumahkuRequest input) {
        if (input.getNomorKtp().length() > 16 || !input.getNomorKtp().matches("\\d+")) {
            throw new BadRequestException("Nomor ktp tidak valid");
        } else {
            return input.getNomorKtp();
        }
    }

    public static boolean emailValidation(String email) {
        String emailRegexFormater = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegexFormater);
        return pattern.matcher(email).matches();
    }

    public List<String> getLookUpKeys(String lookUpGroup) {
        ResponseEntity<List<String>> response = parameterAdaptor.getLookUpKey(lookUpGroup);
        if (ObjectUtils.isEmpty(response.getBody())) {
            throw new BadRequestException("Failed to Get Look Up Keys");
        } else {
            return response.getBody();
        }
    }

    public static String toRoman(int number) {
        String[] romanNumerals = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX",
                "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX",
                "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII",
                "XXIX", "XXX", "XXXI"};
        if (number >= 1 && number <= 31) {
            return romanNumerals[number];
        } else {
            throw new IllegalArgumentException("Day must be between 1 and 31.");
        }
    }
}
