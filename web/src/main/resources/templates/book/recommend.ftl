<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>${title} | 推荐文章</title>
  <meta name="keywords" content="${baseSearchKey},推荐文章,技术进阶">
    <#include "base/key.ftl">
  <link rel="stylesheet" href="/css/recommend.css">
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

</head>
<body>
<#include "base/header.ftl">
<div id="indexBackDiv">
  <h1 class="title">资源推荐</h1>
</div>

<div class="contentDiv">
  <a class="title1">Ⅰ、博文推荐</a>
  <div class="categoryDiv">
      <#list articles as article>
        <a class="remarkSpan"
           style="margin-bottom: 10px"
           href="/page/book/${article.book}/category/${article.slug}">${article.slugName}</a>
      </#list>
  </div>

  <a class="title1">Ⅱ、书籍推荐</a>
  <div class="categoryDiv">
      <#list recommendGroup.BOOK as bookRecommend>
        <a class="title2" href="${bookRecommend.link}" target="_blank">《${bookRecommend.name}》</a>
          <#if bookRecommend.remark != null>
            <span class="remarkSpan">${bookRecommend.remark}</span>
          </#if>
      </#list>
  </div>

  <a class="title1">Ⅲ、学习资源</a>
  <div class="categoryDiv">
      <#list recommendGroup.WEB as recommend>
        <a class="title2" href="${recommend.link}" target="_blank">${recommend.name}</a>
          <#if recommend.remark != null>
            <span class="remarkSpan">${recommend.remark}</span>
          </#if>
      </#list>
  </div>

  <a class="title1">Ⅳ、生产力</a>
  <div class="categoryDiv">
      <#list recommendGroup.PLUGIN as recommend>
        <a class="title2" href="${recommend.link}" target="_blank">${recommend.name}</a>
          <#if recommend.remark != null>
            <span class="remarkSpan">${recommend.remark}</span>
          </#if>
      </#list>
  </div>

</div>
<#include "base/footer.ftl">
</body>
</html>
