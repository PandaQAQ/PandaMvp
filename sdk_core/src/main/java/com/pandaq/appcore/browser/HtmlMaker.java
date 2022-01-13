package com.pandaq.appcore.browser;

import android.util.Log;

import com.pandaq.appcore.utils.system.AppUtils;
import com.pandaq.appcore.utils.system.DisplayUtils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huxinyu on 2019/8/29.
 * Email : panda.h@foxmail.com
 * webView加载 html 富文本片段的帮助类
 */
public class HtmlMaker {

    public static final String BASE_URL = "file:///android_asset/";
    public static final String MIME_TYPE = "text/html";
    public static final String ENCODING = "utf-8";
    public static final String FAIL_URL = "http://daily.zhihu.com/";
    private static final String contentSize = DisplayUtils.sp2px(AppUtils.getContext(), 16f) + "px";
    private static final String lineHeigh = DisplayUtils.sp2px(AppUtils.getContext(), 16f) + 20 + "px";
    private static final String HTMLCORE = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "\t<title>HtmlMaker</title>\n" +
            "\t<style type=\"text/css\">\n" +
            "body{\n" +
            " width:100%;\n" +
            " height:100%\n" +
            " word-wrap:break-word;\n" +
            " }\n" +
            "\n" +
            " p{\n" +
            " line-height: " + lineHeigh + " !important;\n" +
            " font-size: " + contentSize + " !important;\n" +
            " color:rgb(105, 105, 105) !important;\n" +
            " font-family:Microsoft YaHei !important;\n" +
            " width:100% !important;\n" +
            " height:100% !important;\n" +
            " }\n" +
            "\n" +
            " div{\n" +
            " line-height: " + lineHeigh + " !important;\n" +
            " font-size: " + contentSize + " !important;\n" +
            " color:rgb(105, 105, 105) !important;\n" +
            " font-family:Microsoft YaHei !important;\n" +
            " width:100% !important;\n" +
            " height:100% !important;\n" +
            " }\n" +
            "\n" +
            " a{\n" +
            " line-height: " + lineHeigh + " !important;\n" +
            " font-size: " + contentSize + " !important;\n" +
            " color:rgb(105, 105, 105) !important;\n" +
            " font-family:Microsoft YaHei !important;\n" +
            " width:100% !important;\n" +
            " height:100% !important;\n" +
            " }\n" +
            "\n" +
            " span{\n" +
            " line-height: " + lineHeigh + " !important;\n" +
            " font-size: " + contentSize + " !important;\n" +
            " color:rgb(105, 105, 105) !important;\n" +
            " font-family:Microsoft YaHei !important;\n" +
            " width:100% !important;\n" +
            " height:100% !important;\n" +
            " }\n" +
            "\n" +
            " body{\n" +
            " line-height: " + lineHeigh + " !important;\n" +
            " font-size: " + contentSize + " !important;\n" +
            " color:rgb(105, 105, 105) !important;\n" +
            " font-family:Microsoft YaHei !important;\n" +
            " width:100% !important;\n" +
            " height:100% !important;\n" +
            " }\n" +
            " img{\n" +
            " line-height: " + lineHeigh + " !important;\n" +
            " font-size: " + contentSize + " !important;\n" +
            " color:rgb(105, 105, 105) !important;\n" +
            " font-family:Microsoft YaHei !important;\n" +
            " width:100% !important;\n" +
            " height:100% !important;\n" +
            " }\n" +
            "\t</style>\n" +
            "</head>\n" +
            "<body>\n" +
            "\tbody_holder\n" +
            "</body>\n" +
            "</html>";
    private static final String CSS_LINK_PATTERN = " <link href=\"%s\" type=\"text/css\" rel=\"stylesheet\" />";
    private static final String DIV_IMAGE_PLACE_HOLDER = "class=\"img-place-holder\"";
    private static final String DIV_IMAGE_PLACE_HOLDER_IGNORED = "class=\"img-place-holder-ignored\"";

    public static String buildHtml(String html) {
        StringBuilder result = new StringBuilder(html);
        return HTMLCORE.replace("body_holder", result);
    }

    /**
     * 从 HTML 中获取所有的图片链接
     *
     * @param html 待解析的 HTML 代码
     * @return 正则匹配到的图片链接
     */
    public static ArrayList<String> getImageUrlsFromHtml(String html) {
        ArrayList<String> imageSrcList = new ArrayList<>();
        Pattern p = Pattern.compile("<img\\b[^>]*\\bsrc\\b\\s*=\\s*(['\"])?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic)\\b)[^>]*>", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(html);
        String quote;
        String src;
        while (m.find()) {
            quote = m.group(1);
            src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("//s+")[0] : m.group(2);
            imageSrcList.add(src);
        }
        if (imageSrcList.size() == 0) {
            Log.e("imageSrcList", "资讯中未匹配到图片链接");
            return null;
        }
        return imageSrcList;
    }
}
