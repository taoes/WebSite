<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="description" content="${desc}"/>
  <title>${book.title} | 燕归来兮</title>
    <#include "base/key.ftl">

</head>
<body>
<#include "./base/header.ftl">

<div class="contentDiv">
  <h1 style="margin-top: 0" class="bookTitle title is-2">${book.title}</h1>
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
          </#if>
      </#list>
  </div>
</div>
<#include "base/footer.ftl">

</body>
<style>
  .contentDiv {
    width: 100%;
    background-color: #FFFFFF;
    min-height: 1000px;
    height: fit-content;
    display: flex;
    display: -webkit-flex;
    flex-direction: column;
    align-items: center;
    margin-top: 40px;
  }

  @media screen and (min-width: 1000px) {
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

  @media screen and (max-width: 1000px) {
    #categoryDiv {
      width: 95%;
      margin-top: 20px;
      margin-left: 10%;
      margin-right: 10%;
      border-radius: 10px;
      background-color: #FFFFFF;
      padding: 20px;
    }
  }

  .categoryItem {
    display: block;
    margin-top: 10px;
    color: black;
    cursor: pointer;
  }


  .title1 {
    font-size: 25px;
    margin-top: 10px;
    margin-bottom: 10px;
    margin-left: 10px;
    font-weight: 900;
    cursor: pointer;
    font-width: 900;
    width: fit-content;
    line-height: 1.5;
    color: black;
  }


  .title2 {
    font-size: 18px;
    margin-left: 40px;
    margin-top: 10px;
    margin-bottom: 10px;
    cursor: pointer;
    width: fit-content;
    line-height: 1.5;
    color: black;
  }


  /*  标题样式*/
  .bookTitle {
    margin-top: 20px;
    line-height: 1.5;
    font-weight: 900;
    color: black;
    text-align: center;
  }

  a {
    display: block;
  }

</style>
</html>
