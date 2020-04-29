<link href="https://cdn.bootcdn.net/ajax/libs/bulma/0.8.2/css/bulma.css" rel="stylesheet">

<nav class="navbar" role="navigation" aria-label="main navigation">

  <div id="navbarBasicExample" class="navbar-menu" style="height:60px;border-bottom: 1px lightgrey solid">

    <a class="navbar-item" href="">
      <img src="https://cdn.jsdelivr.net/gh/vmlite/s/bulma/images/bulma-logo.png" width="112"
           height="28">
    </a>

    <div class="navbar-start">
      <a class="navbar-item" href="${path}/">
        主页
      </a>

      <a class="navbar-item" href="${path}/page/doc">
        文档
      </a>

      <a class="navbar-item" href="https://www.yuque.com/zhoutao123">
        语雀
      </a>

      <a class="navbar-item" href="${path}/page/blog/123">
        博客
      </a>

      <a class="navbar-item" href="${path}/page/code/123">
        代码片段
      </a>

      <div class="navbar-item has-dropdown is-hoverable">
        <a class="navbar-link">
          更多
        </a>

        <div class="navbar-dropdown">
          <a class="navbar-item" href="${path}/page/about.html">
            关于
          </a>
          <a class="navbar-item" href="${path}/page/link/123">
            链接
          </a>
          <hr class="navbar-divider">
          <a class="navbar-item" href="${path}/page/support/123">
            赞助
          </a>


        </div>
      </div>
    </div>

    <div class="navbar-end">
      <div class="navbar-item">
        <div class="buttons">
          <a class="button is-primary">
            <strong>登录系统</strong>
          </a>
        </div>
      </div>
    </div>
  </div>
</nav>
