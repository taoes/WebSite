<div id="footer">
  <span class="footerFont"> © 2019－2020 zhoutao123.com, all rights reserved 燕归来兮 </span>
  <a class="footerFont link" href="http://www.beian.miit.gov.cn" target="_blank">
    备案号： 皖ICP备17025334号-1
  </a>
</div>

<style>
  #footer {
    display: flex;
    display: -webkit-flex;
    flex-direction: row;
    background-color: #515a6e;
    justify-content: center;
    align-items: center;
    margin-top: 40px;
  }

  .footerFont {
    color: white;
  }
</style>

<script>
  let openBaiduPush = '${config["BAIDU_PUSH"]}';
  if (openBaiduPush === 'true') {
    console.debug("开始推送到百度")
    var _hmt = _hmt || [];
    (function () {
      var hm = document.createElement("script");
      hm.src = "https://hm.baidu.com/hm.js?3065f50daa4f77563d21f9e02c330c78";
      var s = document.getElementsByTagName("script")[0];
      s.parentNode.insertBefore(hm, s);
    })();
  }

  (function(){
    var bp = document.createElement('script');
    var curProtocol = window.location.protocol.split(':')[0];
    if (curProtocol === 'https') {
      bp.src = 'https://zz.bdstatic.com/linksubmit/push.js';
    }
    else {
      bp.src = 'http://push.zhanzhang.baidu.com/push.js';
    }
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(bp, s);
  })();
</script>