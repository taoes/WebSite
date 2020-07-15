<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="description" content="${desc}"/>
  <title>${book.title} | 燕归来兮</title>
  <meta name="keywords" content="${baseSearchKey},${book.title},${book.subTitle}">
  <link rel="stylesheet" href="/css/category.css">
    <#include "base/key.ftl">

</head>
<body>
<#include "./base/header.ftl">

<div class="contentDiv">
  <h1 style="margin-top: 0" class="bookTitle title is-2">${book.title}</h1>
  <span class="title is-6">${book.subTitle}</span>
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
</html>
