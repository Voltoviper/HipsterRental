<%@ include file="../html/header/header_sub.jsp" %>

<%@ page import="wak.system.server.Seitenaufbau" %>

<ol class="breadcrumb">
    <li><a href="../index.jsp">Home</a></li>
    <li class="active">Warenkorb</li>
</ol>
</div>
</div>
<div class="main"><!-- start main -->
    <div class="container">


        <div class="row details"><!-- start details -->
            <p class="h2">Warenkorb</p>
            <table style="width: 100%; valign:top" border="0">
                <tr>
                    <%Seitenaufbau.getWarenkorb(out, request.getCookies(), request.getParameter("addid"));%>
                </tr>
            </table>

        </div><!-- end  details -->


    </div>
</div>
<%@include file="../html/footer/footer.jsp" %>
