<%

    response.setContentType( "text/css" );

    String toPage = request.getParameter("page") == null ? "" : request.getParameter("page");

    if( toPage.equals("") ){

        %><jsp:include page="./default.css" /><%

    }

    if( toPage.equals("home") ){

        %><jsp:include page="./default.css" /><%

    }

%>