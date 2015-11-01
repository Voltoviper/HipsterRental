<%--
  Created by IntelliJ IDEA.
  User: Christoph Nebendahl
  Date: 01.11.2015
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../html/header/header_sub.html" %>
<%@page import="wak.system.server.*" %>
<table style="width:100%; valign:top; border-spacing: 0pt" border="0">
    <tr class="menu">
        <td colspan="3" class="menu"><% Seitenaufbau.getMenu(out, request.getCookies());%>
        </td>
    </tr>
    <tr>
        <td class="kat_nav">
            <table width="100%">
                <%Seitenaufbau.getKategorie(out);%>
            </table>
        </td>
        <td class="main" valign="top">
            <table style="width: 100%; valign:top" border="0">
                <th style="text-align: center" colspan="3">Registrieren</th>
                <tr>
                   <td>
                       <form method="post" action="/Registrierung">
                           <table>
                               <tr>
                                   <td>Vorname: </td><td><input type="text" name="vorname"></td>
                               </tr>
                               <tr>
                                   <td>Nachname: </td><td><input type="text" name="nachname"></td>
                               </tr><tr>
                               <td>Strasse: </td><td><input type="text" name="strasse"></td>
                           </tr>
                               <tr>
                                   <td>Hausnummer: </td><td><input type="number" name="hausnummer"></td>
                               </tr><tr>
                               <td>PLZ: </td><td><input type="text" name="plz"></td>
                           </tr>
                               <tr>
                                   <td>Ort: </td><td><input type="text" name="ort"></td>
                               </tr>
                               <tr>
                                   <td>Handynummer: </td><td><input type="text" name="handynummer"></td>
                               </tr>
                               <tr>
                                   <td>Telefonnummer: </td><td><input type="text" name="telefonnummer"></td>
                               </tr>
                               <tr>
                                   <td>Benutzername: </td><td><input type="text" name="benutzername"></td>
                               </tr>
                               <tr>
                                   <td>E-Mail: </td><td><input type="text" name="email"></td>
                               </tr>
                               <tr>
                                   <td>Kennwort: </td><td><input type="password" name="kennwort"></td>
                               </tr>
                               <tr>
                                   <td><input type="submit" name="registrieren" value="Registrieren"></td>
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

<%@include file="../html/footer/footer.html" %>
