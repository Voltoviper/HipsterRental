<%@ include file="../../html/header/header_subsub.html"%>
<%@page import="wak.system.server.Login" %>
<%@ page import="wak.system.server.Seitenaufbau" %>
<table style="width:100%; valign:top; border-spacing: 0pt" border="0">
  <tr class="menu">
    <td colspan="3" class="menu"><% Seitenaufbau.getMenu(out, request.getCookies());%>
    </td>
  </tr>
  <tr>
    <td class="kat_nav">
      <table width="100%">
        <%Seitenaufbau.getMitarbeiterMenu(out, request.getCookies());%>
      </table>
    <td class="main" valign="top">
      <table style="width: 100%; valign:top" border="0">
        <th style="text-align: center" colspan="3">Neue Bestellung</th>
        <tr>
          <td>
            <form method="post" action="">
              <table style="width:100%">
                <tr>
                  <td>Kunde</td><td>
                    <select name="kundeid" size="1" style="width: 200px;">
                    <%Seitenaufbau.getKundenSelect(out);%></select>
                  </td>
                </tr>
                <tr>
                  <td>
                    Artikel:
                  </td><td>
                  <select name="produkte" size="5" multiple>
                    <%Seitenaufbau.getProdukteDatalist(out);%>
                    </select>
                </td>
                </tr>
                <tr>
                  <td>
                    Von:
                  </td><td>

                  <input type="Text" name="von" id="von" ><img src="../../img/calender/cal.gif" onclick="javascript:NewCssCal('von','ddMMyyyy','arrow', 'true', '24')" style="cursor:pointer"/>
                </td>
                </tr>
                <tr>
                  <td>Bis:</td>
                  <td>
                    <input type="Text" name="bis" id="bis" ><img src="../../img/calender/cal.gif" onclick="javascript:NewCssCal('bis','ddMMyyyy','arrow', 'true', '24')" style="cursor:pointer"/>
                  </td>
                </tr>
                <tr>
                  <td><input type="submit" value="Eintragen"></td>
                </tr>
              </table>
            </form>
          </td>
        </tr>
      </table>
    </td>
    <%Login.getLogin(out, request.getCookies());%>

  </tr>
</table>

<%@include file="../../html/footer/footer.jsp"%>

