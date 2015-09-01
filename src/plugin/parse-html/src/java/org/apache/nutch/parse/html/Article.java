package org.apache.nutch.parse.html;

import org.w3c.dom.Node;

import java.util.Date;

/**
 * Created by vishalshukla on 29/08/15.
 */
public class Article {
  private String articleTitle;
  private String articleContent;
  private Date publishedDate;
  private String content;
  private String articleLink;
  private Node articleTitleNode;

  public String getArticleTitle() {
    return articleTitle;
  }

  public void setArticleTitle(String articleTitle) {
    this.articleTitle = articleTitle;
  }

  public Date getPublishedDate() {
    return publishedDate;
  }

  public void setPublishedDate(Date publishedDate) {
    this.publishedDate = publishedDate;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getArticleLink() {
    return articleLink;
  }

  public void setArticleLink(String articleLink) {
    this.articleLink = articleLink;
  }

  public Node getArticleTitleNode() {
    return articleTitleNode;
  }

  public void setArticleTitleNode(Node articleTitleNode) {
    this.articleTitleNode = articleTitleNode;
  }

  public String getArticleContent() {
    return articleContent;
  }

  public void setArticleContent(String articleContent) {
    this.articleContent = articleContent;
  }
}
