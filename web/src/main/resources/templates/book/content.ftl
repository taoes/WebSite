<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>${title} | 燕归来兮</title>
  <meta name="description" content="${desc}"/>
  <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.0/jquery.min.js"></script>
    <#include "base/key.ftl">
</head>
<body>
<#include "./base/header.ftl">


<div class="contentDiv">
  <h1 style="margin-top: 40px;font-size: 30px" class="title">${title}</h1>
  <div class="detailDiv">
    <div id="content">
      <div>
        <a class="button  is-small"
           href="https://www.yuque.com/zhoutao123/${bookName}/${slug}#lark-mini-editor">
          评论通道
        </a>
        <a class="button  is-small"
                <#if bookId != 0>
          href="/page/book/${bookId}"
        <#else>
          href="/page/blog"
                </#if>>
          返回目录
        </a>
        <a class="button  is-small"
           href="/">
          返回首页
        </a>
        <a class="button  is-small"
           onclick="cleanCache()">
          清除缓存
        </a>
        <hr class="split-pane-divider">
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
    <div id="side">

      <div class="card" style="width: 100%;height: fit-content">
        <header class="card-header">
          <p class="card-header-title">
            目录索引
          </p>
        </header>
        <div class="card-content">
          <div class="content" id="menuContent"></div>
        </div>
      </div>


      <div class="card" style="width: 100%;height: fit-content">
        <header class="card-header">
          <p class="card-header-title">
            最近变更
          </p>
        </header>

        <div class="card-content">
          <div class="content">
              <#list updateRecord as record>
                <a href="/page/book/${record.bookSlug}/category/${record.slug}"
                   style="cursor: pointer;font-size: 12px;color: #4a4a4a">
                  <span
                      style="font-weight: bold">✏️ ${record.updatedAt?string('MM-dd HH:mm:ss')}</span>
                    <#if record.activeType == 'update'>
                      <span style="color:#0088EE;">更新了</span>
                    <#elseif  record.activeType == 'publish'>
                      <span style="color:lightseagreen;">发布了</span>
                    <#else >
                      <span style="color:orangered">删除了</span>
                    </#if>
                  <span style="font-weight: 900;color: #4a4a4a">${record.bookName} </span>
                  中的
                  <span style="font-weight: 900;color: #4a4a4a">${record.slugName}</span>
                </a>
                <br>
              </#list>
          </div>
        </div>
      </div>

      <div class="card" style="width: 100%;height: fit-content">
        <header class="card-header">
          <p class="card-header-title">
            其他站点
          </p>
        </header>

        <div class="card-content">
          <div class="content">
            <p onclick="openNewBookPage(${book.id})" style="cursor: pointer;font-size: 12px">
              Github</p>
          </div>
        </div>
      </div>

      <div class="card" style="width: 100%;height: fit-content">
        <header class="card-header">
          <p class="card-header-title">
            热门推荐
          </p>
        </header>

        <div class="card-content">
          <div class="content">
              <#list bookList as book>
                <p onclick="openNewBookPage(${book.id})"
                   style="cursor: pointer;font-size: 12px">📔 ${book.title}</p>
              </#list>
          </div>
        </div>
      </div>

    </div>
  </div>
</div>


<#include "base/footer.ftl">
</body>
<style>


  .lake-codeblock-content {
    max-width: 100% !important;
  }


  /*  调整图片 变形的问题*/
  .lake-drag-image {
    max-width: 95% !important;
    height: auto !important;
  }

  .contentDiv {
    width: 100%;
    min-height: 1024px;
    background-color: #EFEFEF;
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
    box-shadow: 1px 1px 10px 1px lightgrey;
    background-color: #FFFFFF;
  }


  .title {
    font-weight: 900;
    margin-top: 20px;
    font-width: 900;
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
      padding: 20px;
      flex-grow: 1;
      border-radius: 10px;
      box-shadow: 1px 1px 10px 1px lightgrey;
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
      padding: 20px;
      flex-grow: 1;
      border-radius: 10px;
      box-shadow: 1px 1px 10px 1px lightgrey;
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
      box-shadow: 1px 1px 10px 1px lightgrey;
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
      box-shadow: 1px 1px 10px 1px lightgrey;
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
    margin-top: 10px;
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

</style>
<script>

  <#--  查询文章标题-->
  $(document).ready(function (e) {
    $("div[data-lake-element='root']").children().each(function (index, element) {
      let thisObj = $(this);
      let tagName = thisObj.get(0).tagName;
      if (tagName.substr(0, 1).toUpperCase() === "H") {
        let contentH = thisObj.html();//获取内容
        let markid = "mark-" + tagName + "-" + index.toString();
        thisObj.attr("id", markid);
        if (contentH == null || contentH.trim().length === 0 || contentH.startsWith('<br>')) {
          return
        }
        $("#menuContent").append(
            "<a href='#" + markid + "' style='color:black;cursor: pointer;font-size: 12px'>"
            + '🌲 '
            + contentH + "</a> </br>");

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
      alert("缓存清理完成");
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
