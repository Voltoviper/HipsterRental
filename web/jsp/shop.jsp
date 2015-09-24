<%@ include file="../html/header/header_sub.html"%>
<%@page import ="wak.system.server.*"%>


<table style="width:100%" border="1">
  <tr>
    <td colspan="3">&nbsp;
    </td>
  </tr>
  <tr>
    <td class="kat_nav">
      Kategorien
    </td>
    <td class="main">
      <table style="width: 100%" border="1">
        <th style="text-align: center" colspan="3">Unsere Highlights</th>
        <%Empfehlung.getEmpfehlungen(out);%>

      </table>
    </td>

<% Login.getLogin(out, request.getCookies());%>
  </tr>
</table>

<%@include file="../html/footer/footer.html"%>
