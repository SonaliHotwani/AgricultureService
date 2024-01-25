package com.apple.agriculture.domain;

import lombok.Getter;

public enum State {
    MAHARASHTRA("Maharashtra"),
    ANDAMAN_AND_NICOBAR_ISLANDS("Andaman and Nicobar Islands"),
    ANDHRA_PRADESH("Andhra Pradesh"),
    ARUNACHAL_PRADESH("Arunachal Pradesh"),
    ASSAM("Assam"),
    BIHAR("Bihar"),
    CHANDIGARH("Chandigarh"),
    CHHATTISGARH("Chhattisgarh"),
    DADRA_AND_NAGAR_HAVELI("Dadra and Nagar Haveli"),
    DAMAN_AND_DIU("Daman and Diu"),
    DELHI("Delhi"),
    GOA("Goa"),
    GUJARAT("Gujarat"),
    HARYANA("Haryana"),
    HIMACHAL_PRADESH("Himachal Pradesh"),
    JAMMU_AND_KASHMIR("Jammu and Kashmir"),
    JHARKHAND("Jharkhand"),
    KARNATAKA("Karnataka"),
    KERALA("Kerala"),
    LADDAKH("Laddakh"),
    MADHYA_PRADESH("Madhya Pradesh"),
    MANIPUR("Manipur"),
    MEGHALAYA("Meghalaya"),
    MIZORAM("Mizoram"),
    NAGALAND("Nagaland"),
    ODISHA("Odisha"),
    PUDUCHERRY("Puducherry"),
    PUNJAB("Punjab"),
    RAJASTHAN("Rajasthan"),
    SIKKIM("Sikkim"),
    TAMIL_NADU("Tamil Nadu"),
    TELANGANA("Telangana"),
    TRIPURA("Tripura"),
    UTTAR_PRADESH("Uttar Pradesh"),
    UTTARAKHAND("Uttarakhand"),
    WEST_BENGAL("West Bengal");

    @Getter
    private final String state;

    State(String state) {
       this.state = state;
    }

    public static State getByValue(String stateString) {
        for (State state : values()) {
            if (state.getState().equalsIgnoreCase(stateString)) {
                return state;
            }
        }
        throw new IllegalArgumentException("No enum constant found for state: " + stateString);
    }
}
