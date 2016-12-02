package com.sarah.shopping_cart.models;

public class CD extends Item {

    private final String artist;
    private final String musicFilePath;

    private CD(long id, String title, int price, String artist, String musicFilePath) {
        super(id, title, price);
        this.artist = artist;
        this.musicFilePath = musicFilePath;
    }

    public String getArtist() {
        return artist;
    }

    public String getMusicFilePath() {
        return musicFilePath;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends Item.Builder<Builder, CD> {

        private String artist;
        private String musicFilePath;

        public Builder setArtist(String artist) {
            this.artist = artist;
            return this;
        }

        public Builder setMusicFilePath(String musicFilePath) {
            this.musicFilePath = musicFilePath;
            return this;
        }

        @Override
        public CD build() {
            return new CD(id, name, price, artist, musicFilePath);
        }
    }
}
