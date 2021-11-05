<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>

<div class="detailnew">
      <h1>${blog.title}</h1>
      <div class="displaytime">
        <div class="time">20/11/2018</div>
        <div class="share">
          <ul class="newsTool">
            <li class="font">
              <a href="#" class="fontSizePlus" id="largerfont"
                ><span style="font-size: 10px">A</span
                ><span style="font-size: 13px">A</span
                ><span style="font-size: 15px">A</span>[+]</a
              >
              <a href="#" class="fontSizePlusMin"
                ><span style="font-size: 15px">A</span
                ><span style="font-size: 13px">A</span
                ><span style="font-size: 10px">A</span>[-]</a
              >
            </li>
          </ul>
        </div>
      </div>

      <div class="desc">${blog.description}</div>

      <div class="detail" style="width: 100%; height: auto">
        <div>
          <p>${blog.content}</p>
          </div>
        </div>
</div>
<style>
      .newsTool li.font {
        float: right;
      }
      .detailnew h1 {
        font: 700 25px/35px Roboto, Arial, Tahoma;
        color: #004175;
        margin-bottom: 20px;
        text-align: justify;
      }
      .detailnew .anhdaidien img {
        max-width: 550px;
      }
      .displaytime {
        border-bottom: 1px solid #333;
        margin-bottom: 20px;
        overflow: auto;
      }
      .displaytime .time {
        font: 400 12px Roboto, Arial, Tahoma;
        background: url("../images/i-time.png") no-repeat left center;
        padding-left: 25px;
        width: 105px;
        float: left;
      }
      .detailnew .desc {
        font: 700 15px/20px Roboto, Arial, Tahoma;
        color: #333;
        text-align: justify;
      }
      .detailnew .detail {
        font: 400 14px/22px Roboto, Arial, Tahoma;
        color: #333;
        text-align: justify;
        overflow: auto;
      }
      .detailnew .detail * {
        font-family: Roboto, Arial, Tahoma !important;
        font-size: 14px !important;
      }
      .detailnew .detail img {
        margin-top: 18px !important;
        height: auto !important;
        max-width: 650px;
      }
      .detailnew .tacgia {
        font: 700 14px Roboto, Arial, Tahoma;
        color: #333;
        text-align: right;
        margin: 20px 0;
        width: 100%;
      }
      .detail * {
        line-height: 22px;
      }
      .detail table p {
        padding-bottom: 0px;
      }
      .detail p {
        padding-bottom: 10px;
      }
      .detail table p {
        padding-bottom: 0px;
      }
    </style>