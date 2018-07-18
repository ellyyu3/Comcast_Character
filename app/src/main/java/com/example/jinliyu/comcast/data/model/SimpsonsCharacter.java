package com.example.jinliyu.comcast.data.model;

import java.util.List;

/**
 * Simpsons character data model class
 */
public class SimpsonsCharacter {

    private List<SimpsonsCharacter.RelatedTopicsBean> RelatedTopics;

    public List<SimpsonsCharacter.RelatedTopicsBean> getRelatedTopics() {
        return RelatedTopics;
    }

    public void setRelatedTopics(List<SimpsonsCharacter.RelatedTopicsBean> relatedTopics) {
        RelatedTopics = relatedTopics;
    }



    public static class RelatedTopicsBean {

        private SimpsonsCharacter.RelatedTopicsBean.IconBean Icon;
        private String Result;
        private String Text;
        private String FirstURL;

        public SimpsonsCharacter.RelatedTopicsBean.IconBean getIcon() {
            return Icon;
        }

        public void setIcon(SimpsonsCharacter.RelatedTopicsBean.IconBean Icon) {
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

            private String Width;
            private String URL;
            private String Height;

            public String getWidth() {
                return Width;
            }

            public void setWidth(String Width) {
                this.Width = Width;
            }

            public String getURL() {
                return URL;
            }

            public void setURL(String URL) {
                this.URL = URL;
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
