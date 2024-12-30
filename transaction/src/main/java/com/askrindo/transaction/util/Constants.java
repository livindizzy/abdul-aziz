package com.askrindo.transaction.util;

import lombok.Getter;

public class Constants {

    @Getter
    public enum PRODUK {
        MIKRO_RUMAHKU("9001","MIKRO RUMAHKU"),
        MIKRO_BAHARI("9002","MIKRO BAHARI");
        private final String code;
        private final String value;
        PRODUK(String code, String value) {
            this.code = code;
            this.value = value;
        }
        public String getCode() {return this.code;}
        public String getValue() {return this.value;}
    }

    @Getter
    public enum LOOKUP_GORUP {
        AHLI_WARIS("ahli_waris"),
        JENIS_KAPAL("jenis_kapal"),
        ASMIK_INFO_KEPEMILIKAN("asmik_info_kepemilikan"),
        MARINE_HULL_CONSTRACTION("MARINE_HULL_CONSTRUCTION"),
        PENGGUNAAN_KAPAL("penggunaan_kapal");
        private final String value;
        LOOKUP_GORUP(String value) {

            this.value = value;
        }
    }

    @Getter
    public enum ASURANSI_MIKRO_RUMAHKU {
        SILVER("Silver ","40.000"),
        GOLD("Gold ","50.000"),
        PLATINUM("Platinum","60.000");
        private final String code;
        private final String value;
        ASURANSI_MIKRO_RUMAHKU(String code, String value) {
            this.code = code;
            this.value = value;
        }
        public String getCode() {return this.code;}
        public String getValue() {return this.value;}
    }

}
