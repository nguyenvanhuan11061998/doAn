package com.example.informationrecognize.main.homeFunction.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BannerHomeResponse {
    @SerializedName("errorcode")
    @Expose
    private String errorcode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("listBannerHome")
    @Expose
    private List<BannerHome> listBannerHome;

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<BannerHome> getListBannerHome() {
        return listBannerHome;
    }

    public void setListBannerHome(List<BannerHome> listBannerHome) {
        this.listBannerHome = listBannerHome;
    }

    public class BannerHome {
        @SerializedName("imageBanner")
        @Expose
        private String imageBanner;
        @SerializedName("linkWeb")
        @Expose
        private String linkWeb;

        public String getImageBanner() {
            return imageBanner == null ? "" : imageBanner;
        }

        public void setImageBanner(String imageBanner) {
            this.imageBanner = imageBanner;
        }

        public String getLinkWeb() {
            return linkWeb == null ? "" : linkWeb;
        }

        public void setLinkWeb(String linkWeb) {
            this.linkWeb = linkWeb;
        }
    }
}
