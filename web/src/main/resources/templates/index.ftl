<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>${title} | 不忘初心、方得始终</title>
  <meta name="keywords" content="代码,技术,Java,Java,Docker,Spring,html,开发,${searchKey}">
  <link rel="stylesheet" href="/css/index.css">
    <#include "base/key.ftl">
  <style>
  </style>
</head>
<body>

<#include "./base/header.ftl">

<div>
  <img src="${config["INDEX_IMG"]}" alt="" id="indexImg">
</div>

<div id="share">

  <div id="noteListDiv">
    <h1 class="title">
      <span class="icon has-text-info">
        <i class="fa fa-envira"></i>
    </span>
      我的读书笔记
    </h1>
    <div class="list noteList">
        <#list bookList as book>
          <div class="bookDiv">
            <a href="/page/book/${book.id}" style="display: block">
              <img src="${book.coverImgUrl}" alt="${book.title}" class="cover">
              <h1 class="title" style="font-size: 18px;margin-top: 10px;text-align: center">${book.title}</h1>
            </a>
          </div>
        </#list>
    </div>
  </div>


  <div id="githubDiv">

    <H1 class="title">

      <span class="icon has-text-info">
        <i class="fa fa-envira"></i>
      </span>
      我的开源作品
    </H1>

    <div class="list github">
        <#list githubList as github>
          <div class="bookDiv">
            <img src="${github.coverImgUrl}" alt="${github.title}" class="cover"
                 onclick="openGithubPage('${github.linkUrl}')">
            <h1 class="title" style="font-size: 18px;margin-top: 10px;text-align: center">${github.title}</h1>
          </div>
        </#list>
    </div>

  </div>

</div>
<#include "base/footer.ftl">
</body>
<script>
  function openBookPage(bookId) {
    window.location.href = `${path}/page/book/` + bookId
  }

  function openGithubPage(githubUrl) {
    window.open(githubUrl, "_blank");
  }
</script>

</html>
