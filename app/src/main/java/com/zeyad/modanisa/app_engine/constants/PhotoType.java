package com.zeyad.modanisa.app_engine.constants;

/**
 * Created by Zeyad Assem on 12/04/17.
 * This class represents the photo types in Flicker, and its string representation.
 */
public enum PhotoType {
    SMALL_SQUARE{
        @Override
        public String toString() {
            return "s";
        }
    },
    LARGE_SQUARE{
        @Override
        public String toString() {
            return "q";
        }
    },
    THUMBNAIL{
        @Override
        public String toString() {
            return "t";
        }
    },
    SMALL_240{
        @Override
        public String toString() {
            return "m";
        }
    },
    SMALL_320{
        @Override
        public String toString() {
            return "n";
        }
    },
    MEDIUM{
        @Override
        public String toString() {
            return "-";
        }
    },
    MEDIUM_640{
        @Override
        public String toString() {
            return "z";
        }
    },
    MEDIUM_800{
        @Override
        public String toString() {
            return "c";
        }
    },
    LARGE_1024{
        @Override
        public String toString() {
            return "b";
        }
    },
    LARGE_1600{
        @Override
        public String toString() {
            return "h";
        }
    },
    LARGE_2048{
        @Override
        public String toString() {
            return "k";
        }
    },
    ORIGINAL{
        @Override
        public String toString() {
            return "o";
        }
    }


}
