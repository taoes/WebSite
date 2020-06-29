<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>${title} | ${bookNameOfCN} | 燕归来兮</title>
  <meta name="description" content="${desc}"/>
  <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.0/jquery.min.js"></script>
    <#include "./base/key.ftl">
</head>
<style>

  body {
    background-color: rgba(0, 0, 0, 0.75)
  }


  #indexBackDiv {
    height: 340px;
    background: url(${config['CONTENT_IMG']}) no-repeat 0 63%;
    background-size: cover;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .title {
    font-weight: 900;
    font-width: 900;
    font-size: 50px;
    color: #fff;
    text-align: center;
  }

  .lake-codeblock-content {
    max-width: 100% !important;
  }

  .lake-table {
    width: 100% !important;
  }


  /*  调整图片 变形的问题*/
  .lake-drag-image {
    max-width: 95% !important;
    height: auto !important;
    margin: auto;
  }

  .contentDiv {
    width: 100%;
    min-height: 1024px;
    background-color: #FFFFFF;
    padding-bottom: 20px;
    height: fit-content;
    display: flex;
    display: -webkit-flex;
    flex-direction: column;
    align-items: center;
  }

  #content {
    width: 80%;
    margin-top: 20px;
    padding: 20px;
    flex-grow: 1;
    border-radius: 10px;
    background-color: #FFFFFF;
  }

  .divider {
    height: 1px;
    margin: 0;
    background-color: lightgray;
  }


  .title {
    font-weight: 900;
    font-width: 900;
    line-height: 1.5;
    margin-top: 40px;
    font-size: 30px;
  }

  .detailDiv {
    display: flex;
    display: -webkit-flex;
    width: 90%;
    min-height: 300px;
  }

  /*设置卡片的间距*/
  .card {
    margin-bottom: 10px;
    border-radius: 10px;
  }

  @media screen and (max-width: 800px) {

    #content {
      width: 100%;
      margin-top: 20px;
      padding: 10px;
      flex-grow: 1;
      border-radius: 10px;
      background-color: #FFFFFF;
    }

    #side {
      display: none;
    }

    .detailDiv {
      display: flex;
      display: -webkit-flex;
      flex-direction: column;
      width: 100%;
      min-height: 300px;
    }

  }

  @media screen  and (max-width: 1200px) {


    #content {
      width: 100%;
      margin-top: 20px;
      padding: 10px;
      flex-grow: 1;
      border-radius: 10px;
      background-color: #FFFFFF;
    }


    #side {
      display: none;
    }

    .detailDiv {
      display: flex;
      flex-direction: column;
      display: -webkit-flex;
      width: 95%;
      min-height: 300px;
    }
  }

  @media screen  and (min-width: 1200px) {

    #content {
      width: 70%;
      margin-top: 20px;
      padding: 20px;
      flex-grow: 1;
      border-radius: 10px;
      background-color: #FFFFFF;
    }


    #side {
      width: 300px;
      height: 100px;
      display: flex;
      display: -webkit-flex;
      flex-direction: column;
      flex-grow: 0;
      margin: 20px 20px 20px 30px;
      background-color: #FFFFFF;
      border-radius: 10px;
    }

    .detailDiv {
      display: flex;
      display: -webkit-flex;
      width: 90%;
      min-height: 300px;
    }
  }


  /*  按钮样式*/
  .button {
    margin-top: 19px;
    margin-bottom: 20px;
  }

  /*  评论区样式*/

  #commentArea {
    width: 100%;
    min-height: 180px;
    margin-right: 50px;
  }

  #commentInfo {
    display: flex;
    display: -webkit-flex;
    width: 100%;
  }

  .field {
    flex-grow: 1;
  }

  /*  按钮组*/

  .buttons {
    display: flex;
    display: -webkit-flex;
    align-content: space-around;
  }


  #tagList {
    display: flex;
    flex-direction: row;
  }


  .tag {
    margin-left: 30px;
  }


  .tags:last-child {
    margin-bottom: 1rem;
  }

  .listTag {
    cursor: pointer;
    font-size: 13px;
    text-decoration: none !important;
    color: lightslategray !important;
  }
</style>
<body>
<#include "./base/header.ftl">

<div id="indexBackDiv">
  <h1 class="title">${title}</h1>
</div>

<div class="contentDiv">

  <div class="detailDiv">
    <div id="content">
      <div>
        <div id="tagList" class="field is-grouped is-grouped-multiline">
          <div class="tags has-addons">
            <span class="tag">语雀文档</span>
            <a class="tag is-link"
               href="https://www.yuque.com/zhoutao123/${bookName}/${slug}">访问</a>
          </div>


          <div class="tags has-addons">
            <span class="tag">返回目录</span>
            <a class="tag is-link" <#if bookId != 0>href="/page/book/${bookId}"
               <#else>href="/page/blog"</#if> >访问</a>
          </div>

          <div class="tags has-addons">
            <span class="tag">访问次数</span>
            <a class="tag is-success" href="#">${count}</a>
          </div>
        </div>

        <hr class="divider">
        <div class="notification is-link is-light">
          <div class="content" id="menuContent"></div>
        </div>
        <hr class="divider">
      </div>

      <div id="detail">
          ${content}
      </div>
      <div style="height: 50px"></div>

      <hr class="split-pane-divider">
      <div id="comment">
        <div id="commentInfo">
          <div class="field">
            <div class="control has-icons-left">
              <span class="icon has-text-info">
                <i class="fa fa-envira"></i>
              </span>
              <input class="input" id="nickname" type="email"
                     placeholder="您的称呼">
            </div>
          </div>
          <div style="width: 4%"></div>
          <div class="field">
            <div class="control has-icons-left">
              <span class="icon has-text-info">
                <i class="fa fa-envelope"></i>
              </span>
              <input class="input" id="email" type="email"
                     placeholder="您的邮箱">
            </div>
          </div>

          <div style="width: 4%"></div>

          <div class="field">
            <div class="control has-icons-left">
              <span class="icon has-text-info">
                <i class="fa fa-chrome"></i>
              </span>
              <input class="input" id="website" type="text" placeholder="(可选)您的网址">

            </div>
          </div>
        </div>
        <textarea class="textarea" placeholder="请输入您的评论内容" id="commentArea"></textarea>
        <div class="buttons">
          <button class="button is-link" onclick="submitComment()">提交</button>
          <button class="button is-danger" onclick="reset()">重置</button>
        </div>
      </div>

      <div style="height: 50px"></div>

      <hr class="split-pane-divider">
      <div id="commentList">
          <#list  comments as comment>
            <div>
              <article class="media">
                <figure class="media-left">
                  <p class="image is-64x64">
                    <img src="https://pic.zhoutao123.com/picture/index/header.jpeg" alt="头像加载失败">
                  </p>
                </figure>
                <div class="media-content">
                  <div class="content">
                    <p>
                      <strong>
                        <a href="${comment.url}">
                            ${comment.name}
                        </a>
                      </strong><small>${comment.createTime?string('yyyy-MM-dd HH:mm:ss')}</small>
                      <br>
                        ${comment.content}
                    </p>
                  </div>
                  <nav class="level is-mobile">
                    <div class="level-left">
                      <a class="level-item">
                        <span class="icon is-small"><i class="fa fa-reply"></i></span>
                      </a>
                      <a class="level-item">
                        <span class="icon is-small"><i class="fa fa-retweet"></i></span>
                      </a>
                      <a class="level-item">
                        <span class="icon is-small"><i class="fa fa-heart"></i></span>
                      </a>
                    </div>
                  </nav>
                </div>
              </article>
              <hr class="split-pane-divider">
            </div>
          </#list>

      </div>


    </div>
  </div>
</div>


<#include "base/footer.ftl">
</body>
<script>

  <#--  查询文章标题-->
  $(document).ready(function (e) {
    $("div[data-lake-element='root']").children().each(function (index, element) {
      let thisObj = $(this);
      let tagName = thisObj.get(0).tagName;
      if (tagName.substr(0, 1).toUpperCase() === "H") {
        let contentH = thisObj.html();
        let markId = "mark-" + tagName + "-" + index.toString();
        thisObj.attr("id", markId);
        if (contentH == null || contentH.trim().length === 0 || contentH.startsWith('<br>')) {
          return
        }
        let data = "<a  class='listTag' href='#" + markId
            + "''> 🎉  " + contentH
            + "</a> </br>";
        $("#menuContent").append(data);

      }
    });
  });

  function toIndexPage() {
    window.location.href = '/';
  }

  /**
   * 跳转到新的图书页面
   * @param bookId
   */
  function openNewBookPage(bookId) {
    window.open("/page/book/" + bookId)
  }

  /** 打开Github登录页面*/
  function openGithubLoginPage() {
    window.location.href = "https://github.com/login/oauth/authorize?client_id=${clientId}&redirect_uri=${githubCallback}"
  }

  /**
   * 清除缓存信息
   */
  function cleanCache() {
    $.get("/book/cache?cacheKey=CATEGORY:${bookName}:CONTENT:${slug}", function (data, status) {
      console.debug("文章刷新完成")
      location.reload();
    });
  }

  //  新增评论

  function submitComment() {
    let name = $("#nickname").val();
    let email = $("#email").val();
    let url = $("#website").val();
    let content = $("#commentArea").val();
    let bookName = '${bookName}';
    let slug = '${slug}';

    let data = {
      name,
      email,
      url,
      content,
      bookName,
      slug
    }

    $.ajax({
      contentType: 'application/json',
      type: 'POST',
      url: "/book/comment",
      dataType: "json",
      data: JSON.stringify(data),
      success: function (message) {
        window.location.reload()
      }
    });

  }
</script>

</html>
