<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>进制转换 | {title}</title>
  <script src="https://cdn.bootcdn.net/ajax/libs/vue/3.0.0-beta.15/vue.cjs.min.js"></script>
    <#include "base/key.ftl">
</head>
<body>
<#include "component/PcMenu.ftl">


<div id="toolsDiv">

  <div id="numberConverterDiv">

    <h2 class="title is-2">数字进制转换</h2>

    {{message}}
    <span>二进制</span> <input type="number" class="input" placeholder="二进制数据">
    <span>八进制</span> <input type="number" class="input" placeholder="二进制数据">


    <span>十进制</span> <input type="number" class="input" placeholder="二进制数据">
    <span>十六进制</span> <input type="number" class="input" placeholder="二进制数据">

  </div>
</div>
</body>

<style>

  #toolsDiv {
    display: flex;
    display: -webkit-flex;
    flex-direction: row;
    justify-content: center;
  }

  #numberConverterDiv {

  }


  .input {
    margin-top: 5px;
  }
</style>

<script>
   new Vue({
    el: '#toolsDiv',
    data: {
      message: '123'
    }
  })
</script>
</html>
