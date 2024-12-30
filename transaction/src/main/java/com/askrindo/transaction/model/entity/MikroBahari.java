package com.askrindo.transaction.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "MIKRO_BAHARI")
public class MikroBahari extends Auditable<String> implements Serializable {

    @Column(name = "NAMA_TERTANGGUNG",length = 100)
    private String namaTertanggung;

    @Column(name = "NOMOR_KTP",length = 16)
    private String nomorKtp;

    @Column(name = "EMAIL",length = 150)
    private String email;

    @Column(name = "NOMOR_TELEPON",length = 30)
    private String nomorTelepon;

    @Column(name = "JANGKA_WAKTU_AWAL")
    private LocalDateTime jangkaWaktuAwal;

    @Column(name = "JANGKA_WAKTU_AKHIR")
    private LocalDateTime jangkaWaktuAkhir;

    @Column(name = "NO_ID_KAPAL", length = 30)
    private String noIdKapal;

    @Column(name = "JENIS_KAPAL", length = 150)
    private String jenisKapal;

    @Column(name = "KONSTRUKSI_KAPAL", length = 150)
    private String konstruksiKapal;

    @Column(name = "PENGGUNAAN_KAPAL", length = 150)
    private String penggunaanKapal;

    @Column(name = "HARGA_KAPAL")
    private BigDecimal hargaKapal;

    @Column(name = "JENIS_PAKET",length = 150)
    private String jenisPaket;

    @Column(name = "NOMOR_SERTIFIKAT")
    private String nomorSertifikat;

    @Column(name = "NILAI_PREMI")
    private BigDecimal nilaiPremi;
}
