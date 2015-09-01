package org.apache.nutch.parse.html;

import org.apache.hadoop.io.Text;
import org.apache.http.client.ClientProtocolException;
import org.apache.nutch.parse.Outlink;
import org.apache.nutch.parse.ParseData;
import org.apache.nutch.parse.ParseText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vishalshukla on 29/08/15.
 */
public class ArticleParser {
  public static final Logger LOG = LoggerFactory
          .getLogger(ArticleParser.class);
  private DOMContentUtils utils;

  public ArticleParser(DOMContentUtils utils) {
    this.utils = utils;
  }

  public boolean isParsable(DocumentFragment root) {
    try {

      XPath xPath = XPathFactory.newInstance().newXPath();
      NodeList rows = (NodeList) xPath.evaluate("//*[@id=\"adminForm\"]/TABLE/TBODY/TR", root, XPathConstants.NODESET);
      return true;
    } catch (XPathExpressionException e) {
      LOG.info("Not Parseable with xpath /TABLE/TBODY/TR");
    }
    return false;
  }

  public List<Article> parseArticles(DocumentFragment root) {
    List<Article> articles = new ArrayList<>();
    try {

      XPath xPath = XPathFactory.newInstance().newXPath();
      NodeList rows = null;

      rows = (NodeList) xPath.evaluate("//*[@id=\"adminForm\"]/TABLE/TBODY/TR", root, XPathConstants.NODESET);

      for (int i = 0; i < rows.getLength(); ++i) {
        Article article = new Article();
        Node e = rows.item(i);
        Node titleNode = (Node) xPath.evaluate("TD[1]", e, XPathConstants.NODE);
        Node dateNode = (Node) xPath.evaluate("TD[2]", e, XPathConstants.NODE);

        article.setArticleTitleNode(titleNode);
        String titleText = extractText(titleNode);
        String dateText = extractText(dateNode);
        String articleLink = xPath.evaluate("A/@href", titleNode, XPathConstants.STRING).toString();
        article.setArticleTitle(titleText);
        article.setArticleLink(articleLink);
          SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
          try {
            Date publishedDate = sdf.parse(dateText);
            article.setPublishedDate(publishedDate);
          } catch (java.text.ParseException e1) {
            LOG.warn("Couldn't parse date from the feed");
          }

          LOG.info("Parsed article : " + i + 1);
        articles.add(article);
      }

    } catch (XPathExpressionException e) {
      e.printStackTrace();
    }
    return articles;
  }



  private String extractText(Node node) {
    StringBuffer sb = new StringBuffer();
    utils.getText(sb, node); // extract text
    return sb.toString();
  }
}
