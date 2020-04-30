<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>${book.title}</title>
  <#include "base/key.ftl">

</head>
<body>
<#include "./base/header.ftl">

<div class="contentDiv">
  <h1 style="margin-top: 20px" class="bookTitle title is-2">${book.title}</h1>
  <span style="margin-left: 150px" class="title is-6">${book.subTitle}</span>
  <div id="categoryDiv">
      <#list categoryList.data as category>
          <#if category.depth == 1>
            <p class="title1" onclick="openBookContent('${book.linkUrl}','${category.slug}')">
                ${category.title}
            </p>
          <#elseif category.depth == 2>
            <p
                style="margin-left: 40px"
                onclick="openBookContent('${book.linkUrl}','${category.slug}')"
                class="title2">
                ${category.title}
            </p>
          <#else>
            <p
                class="categoryItem"
                style="margin-left: 60px;"
                onclick="openBookContent('${book.linkUrl}','${category.slug}')">
                ${category.title}
            </p>
            <br>

          </#if>
      </#list>
  </div>
</div>
<#include "base/footer.ftl">

</body>
<style>
  .contentDiv {
    width: 100%;
    background-color: #EFEFEF;
    min-height: 1000px;
    height: fit-content;
    display: flex;
    display: -webkit-flex;
    flex-direction: column;
    align-items: center;
  }

  @media screen and (min-width: 1200px) {
    #categoryDiv {
      width: 80%;
      margin-top: 20px;
      margin-left: 10%;
      margin-right: 10%;
      border-radius: 10px;
      background-color: #FFFFFF;
      padding: 20px;
      box-shadow: 1px 1px 10px 1px #515a6e;
    }
  }

  @media screen and (max-width: 1200px) {
    #categoryDiv {
      width: 100%;
      margin-top: 20px;
      margin-left: 10%;
      margin-right: 10%;
      border-radius: 10px;
      background-color: #FFFFFF;
      padding: 20px;
      box-shadow: 1px 1px 10px 1px #515a6e;
    }
  }

  .categoryItem {
    cursor: pointer;
  }


  .categoryItem:hover {
    color: red;
  }

  .title1 {
    font-size: 25px;
    margin-top: 20px;
    margin-left: 10px;
    font-weight: 900;
    cursor: pointer;
  }

  .title1::before {
    content: "✨ ";
  }

  .title2 {
    font-size: 18px;
    margin-left: 40px;
    margin-top: 20px;
    margin-bottom: 10px;
    cursor: pointer;
    color: darkblue;
  }

  .title1:hover, .title2:hover {
    color: #08E;
  }

  /*  标题样式*/
  .bookTitle {
    font-weight: 900;
    margin-top: 20px;
    font-family: Serif, serif;
  }

</style>

<script>

  function openBookContent(bookName, slug) {
    if (slug !== '#') {
      window.open("${path}/page/book/" + bookName + "/category/" + slug, "_blank");
    } else {
      console.debug("不包含文章信息，停止跳转")
    }
  }
</script>
</html>