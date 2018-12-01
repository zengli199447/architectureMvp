package model.bean;

/**
 * Created by Administrator on 2018/2/12.
 */

public class GridViewToolsBean {
    private String Content;
    private String urlPic;

    public GridViewToolsBean(String content, String urlPic) {
        Content = content;
        this.urlPic = urlPic;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getUrlPic() {
        return urlPic;
    }

    public void setUrlPic(String urlPic) {
        this.urlPic = urlPic;
    }
}
