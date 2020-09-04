<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>影子不长 | 燕归来兮</title>
    <#include "base/key.ftl">

</head>
<body>
<#include "component/PcMenu.ftl">

<div id="pictureDiv">


    <#list pictureList as picture>
      <div class="picture">
        <img src="${picture.url}">
      </div>
    </#list>

</div>
<style>


</style>
</body>
</html>
