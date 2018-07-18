package com.example.jinliyu.comcast.data.model;

import java.util.List;

/**
 * Wire character data model class
 */
public class WireCharacter {

    private List<WireCharacter.RelatedTopicsBean> RelatedTopics;

    public List<WireCharacter.RelatedTopicsBean> getRelatedTopics() {
        return RelatedTopics;
    }

    public void setRelatedTopics(List<WireCharacter.RelatedTopicsBean> relatedTopics) {
        RelatedTopics = relatedTopics;
    }


    public static class RelatedTopicsBean {

        private WireCharacter.RelatedTopicsBean.IconBean Icon;
        private String Result;
        private String Text;
        private String FirstURL;

        public WireCharacter.RelatedTopicsBean.IconBean getIcon() {
            return Icon;
        }

        public void setIcon(WireCharacter.RelatedTopicsBean.IconBean Icon) {
            this.Icon = Icon;
        }

        public String getResult() {
            return Result;
        }

        public void setResult(String Result) {
            this.Result = Result;
        }

        public String getText() {
            return Text;
        }

        public void setText(String Text) {
            this.Text = Text;
        }

        public String getFirstURL() {
            return FirstURL;
        }

        public void setFirstURL(String FirstURL) {
            this.FirstURL = FirstURL;
        }

        public static class IconBean {


            private String URL;
            private String Width;
            private String Height;

            public String getURL() {
                return URL;
            }

            public void setURL(String URL) {
                this.URL = URL;
            }

            public String getWidth() {
                return Width;
            }

            public void setWidth(String Width) {
                this.Width = Width;
            }

            public String getHeight() {
                return Height;
            }

            public void setHeight(String Height) {
                this.Height = Height;
            }
        }
    }
}
