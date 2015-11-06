<%--
  Created by IntelliJ IDEA.
  User: Christoph Nebendahl
  Date: 04.11.2015
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../html/header/header_sub.jsp" %>
<%@ page import="wak.system.server.Seitenaufbau" %>
<ol class="breadcrumb">
    <li><a href="../index.jsp">Home</a></li>
    <%Seitenaufbau.getKategorie(out);%>
</ol>
</div>
</div>
<div class="main"><!-- start main -->
    <div class="container">


        <div class="row details"><!-- start details -->
            <p class="h2">Kategorie</p>
            <table style="width: 100%; valign:top" border="0">
                <tr>
                    <td><p class="para">Upps, da ist wohl etwas schiefgegangen</p></td>
                </tr>
            </table>

        </div><!-- end  details -->


    </div>
</div>

<%@include file="../html/footer/footer.jsp" %>
