<%@ include file="../../html/header/header_subsub.html"%>
<%@page import="wak.system.server.*" %>
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
    </td>
    <td class="main" valign="top">
      <table style="width: 100%; valign:top" border="0">
        <th style="text-align: center" colspan="3">Bestell&uumlbersicht</th>
        <tr>
          <td>
            <form method="post" action="/GeraetEinfuegen">
              <table>
                <tr>
                  <td>Produktauswahl</td>
                  <td><select name="produkt" size="1" style="width: 200px;">
                      <%Seitenaufbau.getProdukteDatalist(out);%>
                    </select>

                  </td>
                </tr>
                <tr>
                  <td>

                  </td>
                  <td><input type="submit" value="Ger&aumlt hinzuf&uumlgen"></td>
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

<%@include file="../../html/footer/footer.html"%>

