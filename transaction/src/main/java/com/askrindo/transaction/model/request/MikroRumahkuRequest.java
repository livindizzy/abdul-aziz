package com.askrindo.transaction.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MikroRumahkuRequest implements Serializable {

    private String namaTertanggung;
    private String nomorKtp;
    private String email;
    private String nomorTelepon;
    private String jangkaWaktuAwal;
    private String jangkaWaktuAkhir;
    private String informasiKepemilikan;
    private String alamat;
    private String namaAhliWaris;
    private String tanggalLahirAhliWaris;
    private String nomorTeleponAhliWaris;
    private String hubungan;
    private String jenisPaket;

    private String role;
    private String username;

}
