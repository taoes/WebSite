<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>${data.title} | ${data.book.name} | 燕归来兮</title>
  <meta name="keywords" content="${baseSearchKey},${searchKey}">
  <meta name="description" content="${data.description}"/>
  <link rel="stylesheet" href="/css/content.css">
  <style>
    #indexBackDiv {
      height: 340px;
      background: url(${config['CONTENT_IMG']}) no-repeat 0 63%;
      background-size: cover;
      display: flex;
      align-items: center;
      justify-content: center;
    }
  </style>
    <#include "./base/key.ftl">
</head>

<body>
<#include "./base/header.ftl">

<div id="indexBackDiv">
  <h1 class="title">${data.title}</h1>
</div>


<div class="contentDiv">

  <div id="categoryDiv">
      <#list  categoryList as category>
          <#if category.slug != '#'>
            <a class="categoryLink"
               style="margin-left: ${(category.depth - 1 )  * 10}px"
               href="/page/book/${bookName}/category/${category.slug}">
                ${category.title}
            </a>
          <#else >

            <span class="categoryLink"
                  style="margin-left: ${(category.depth - 1 )  * 10}px;font-weight: bolder">
                ${category.title}
            </span>

          </#if>
      </#list>

  </div>

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

  /**
   * 查询文章标题
   */
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
            + "''> 👉🏻  " + contentH
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

  /**
   * 新增评论
   */
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

  // 添加样式
  $(document).ready(function () {
    $("div[data-lake-card='codeblock']").addClass("lake-card-margin");
  })
</script>
</html>
