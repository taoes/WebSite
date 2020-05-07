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
  <h1 style="margin-top: 40px" class="bookTitle title is-2">${book.title}</h1>
  <span style="margin-left: 150px" class="title is-6">${book.subTitle}</span>
  <div id="categoryDiv">
      <#list categoryList.data as category>
          <#if category.depth == 1>
            <a class="title1"
                    <#if category.slug != '#'>
                      href="/page/book/${book.linkUrl}/category/${category.slug}"
                    </#if>
            >
                ${category.title}
            </a>
          <#elseif category.depth == 2>
            <a
                style="margin-left: 40px"
                    <#if category.slug != '#'>
                      href="/page/book/${book.linkUrl}/category/${category.slug}"
                    </#if>
                class="title2">
                ${category.title}
            </a>
          <#else>
            <a
                class="categoryItem"
                style="margin-left: ${category.depth * 20}px;"
                    <#if category.slug != '#'>
                      href="/page/book/${book.linkUrl}/category/${category.slug}"
                    </#if>
            >

                ${category.title}
            </a>
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
      width: 95%;
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
    color: black;
    cursor: pointer;
    font-weight: bolder;
    font-family: Serif, serif;
  }


  .categoryItem:hover {
    color: #0088EE;
    font-weight: 900;
  }

  .title1 {
    font-size: 25px;
    margin-top: 20px;
    margin-left: 10px;
    font-weight: 900;
    cursor: pointer;
    color: black;
    font-width: 900;
    font-family: Serif, serif;
  }

  .title1::before {
    content: "✏️ ";
  }

  .title2 {
    font-size: 18px;
    margin-left: 40px;
    margin-top: 20px;
    margin-bottom: 10px;
    cursor: pointer;
    color: black;
    font-family: Serif, serif;
  }

  .title1:hover, .title2:hover {
    color: #0088EE;
    font-weight: 900;
  }

  /*  标题样式*/
  .bookTitle {
    font-weight: 900;
    margin-top: 20px;
    font-family: Serif, serif;
  }

  a {
    display: block;
  }

</style>
</html>
