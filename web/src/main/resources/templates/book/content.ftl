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
<#include "./component/PcMenu.ftl">

<div id="indexBackDiv">
  <h1 class="title">${data.title}</h1>
</div>


<div style="width: 100%;display: flex;justify-content: center;background-color: white">
  <div class="contentDiv">
    <div id="documentInfo">
      <div>
        <div id="tagList" class="field is-grouped is-grouped-multiline">
          <button class="layui-btn layui-btn-normal layui-btn-sm" onclick="toYuquePage()">访问语雀文档
          </button>
          <button class="layui-btn layui-btn-normal layui-btn-sm" onclick="toCategoryPage()">返回目录
          </button>
          <button class="layui-btn layui-btn-normal  layui-btn-sm" href="#">访问次数:${count}</button>
          <div id="rate" class="rate"></div>
        </div>
      </div>

      <hr class="divider">
      <div class="notification" style="background: none">
        <div class="content" id="menuContent"></div>
      </div>
    </div>
    <div id="detail">
        ${content}
    </div>
    <div style="height: 50px"></div>
    <div id="comment">
      <hr class="split-pane-divider">
      <div id="commentInfo">
        <div class="layui-form-item">
          <label class="layui-form-label">称呼</label>
          <div class="layui-input-block">
            <input type="text" id="nickname" required lay-verify="required" placeholder="您的称呼"
                   autocomplete="off" class="layui-input">
          </div>
        </div>


        <div class="layui-form-item">
          <label class="layui-form-label">邮箱</label>
          <div class="layui-input-block">
            <input type="text" id="email" required lay-verify="required" placeholder="您的邮箱"
                   autocomplete="off" class="layui-input">
          </div>
        </div>

        <div class="layui-form-item">
          <label class="layui-form-label">主页</label>
          <div class="layui-input-block">
            <input type="text" id="website" required lay-verify="required" placeholder="您的网站主页"
                   autocomplete="off" class="layui-input">
          </div>
        </div>

      </div>

      <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">内容</label>
        <div class="layui-input-block">
            <textarea name="desc" placeholder="请输入您的评论内容" class="layui-textarea"
                      id="commentArea"></textarea>
        </div>
      </div>

      <div class="layui-form-item">
        <div class="layui-input-block">
          <button class="layui-btn layui-btn-sm layui-btn-normal" onclick="submitComment()">提交
          </button>
          <button class="layui-btn layui-btn-sm layui-btn-danger" onclick="reset()">重置</button>
        </div>
      </div>
    </div>
    <div style="height: 30px"></div>
    <div id="commentList">
        <#list  comments as comment>
          <div class="commentDiv">
            <img src="https://pic.zhoutao123.com/picture/index/header.jpeg" alt="头像加载失败"
                 width="64px">

            <div class="media-content">
              <div class="content">
                <p>
                  <strong>
                    <a href="${comment.url}" class="commentUrl">
                        ${comment.name}
                    </a>
                  </strong>
                  <small
                      class="commentTime">${comment.createTime?string('yyyy-MM-dd HH:mm:ss')}</small>
                <p class="commentContent">

                    ${comment.content}
                </p>
                </p>
              </div>


            </div>

            <hr class="split-pane-divider">
          </div>
        </#list>
    </div>
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

  String.prototype['format'] = function () {
    const e = arguments;
    return !!this && this.replace(/\{(\d+)\}/g, function (t, r) {
      return e[r] ? e[r] : t;
    });
  };

  $(document).ready(function (e) {
    $("div[data-lake-element='root']").children().each(function (index, element) {
      let thisObj = $(this);
      let tagName = thisObj.get(0).tagName;
      if (tagName.substr(0, 1).toUpperCase() === "H") {
        let contentH = thisObj.html();
        let titleSize = parseInt(tagName.substr(1));
        let markId = "mark-" + tagName + "-" + index.toString();
        thisObj.attr("id", markId);
        if (contentH == null || contentH.trim().length === 0 || contentH.startsWith('<br>')) {
          return
        }
        var dataTemp = "<a  class=\'listTag\' href={0} style='margin-left: {1}px' '> {2} </a></br>";
        $("#menuContent").append('＊' + dataTemp.format("#"+markId, titleSize * 10, contentH));
      }
    });
  });

  function toIndexPage() {
    window.location.href = '/';
  }

  /**
   * 跳转到新的图书页面

   */
  function toCategoryPage() {
    window.location.href = "/page/book/${bookId}";
  }

  function toYuquePage() {
    window.location.href = 'https://www.yuque.com/zhoutao123/${bookName}/${slug}';
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

  layui.use('rate', function () {
    var rate = layui.rate;

    //渲染
    var ins1 = rate.render({
      elem: '#rate',  //绑定元素
      text: true,
      value: ${start},
      choose: function (value) {
        // 发送POST请求
        $.ajax({
          contentType: 'application/json',
          type: 'GET',
          url: "/book_article/start?book=${bookName}&slug=${slug}&value=" + value,
          success: function (message) {
            layer.msg('提交完成，当前文章综合评分:'+message.data);
            this.value = message.data;
          }
        });

        // 本地缓存
        localStorage.setItem("${bookName}/${slug}", value);
      }, setText: function (value) {
        var arrs = {
          '1': '垃圾'
          , '2': '一般'
          , '3': '中等'
          , '4': '良好',
          '5': '优秀'
        };
        this.span.text(arrs[value] || (value + "文章"));
      }
    });
  });
</script>
</html>
