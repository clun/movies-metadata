<%@ include file="/jsp-tiles/taglibs.jsp"%>

<section class="content">
    <h3 style="color:#B0C4DE;margin-top:-10px;margin-left:10px">
        <i class="fa fa-film"></i>&nbsp;${docuDetail.titre} (${docuDetail.id})
        <hr>
    </h3>

    <div class="row">
        <div class="col-xs-12">

            <table style="border:0px">
                <tr>
                    <td style="width:150px">
                        <img id="imgcover" style="position:absolute;top:20px;height:180px;width:130px;border:1px solid #CCCCCC;"
                            src="data:image/jpg;base64,${docuDetail.image}"/>
                        <div style="position:absolute;top:210px;left:40px">
                            <span class="glyphicon glyphicon-star" style="color:#DDDD00"></span>
                            <span class="glyphicon glyphicon-star" style="color:#DDDD00"></span>
                            <span class="glyphicon glyphicon-star" style="color:#DDDD00"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                        </div>
                    </td>
                    <td style="padding-top:0px">
                        <p style="font-weight:bold;font-size:12px"/>Titre Original :
                        <span id="titreOriginal" style="color: #3c8dbc">${docuDetail.titreOriginal}</span>
                        ( <img id="imgpays" src="data:image/jpg;base64,${docuDetail.paysIcone}" style="height:10px;width:16px;" /> )
                        </p>

                        <p style="font-weight:bold;font-size:12px"/>Dur&eacute;e :
                        <span id="duree" style="color: #3c8dbc">${docuDetail.duree}</span>
                        </p>

                        <p style="font-weight:bold;font-size:12px"/>
                        Langue : <img id="imglang" src="data:image/jpg;base64,${docuDetail.langueIcone}"" style="height:10px;width:16px;" />
                        <span id="soustitres">${docuDetail.soustitre}</span>
                        </p>

                        <p style="font-weight:bold;font-size:12px"/>
                        Fichier :
                        <span id="ficformat" style="color:#3c8dbc;font-weight:normal">${docuDetail.format}</span>,
                        <span id="fictaille" style="color:#3c8dbc;font-weight:normal">${docuDetail.taille}</span>,
                        <span id="ficresolution" style="color:#3c8dbc;font-weight:normal">${docuDetail.resolution}</span>
                        </p>

                        <p><span id="description" style="text-align:justify;font-size:12px">${docuDetail.description}</span></p>

                    </td>
                </tr>

            </table>
            <br/>&nbsp;

            <a id="link2Edit"
               class="openModalEdit btn btn-inverse"
               style="position:absolute;top:10px;right:10px"
               data-toggle="modal"
               data-uid="${docuDetail.id}"
               data-target="#modalEdit"><i class="fa fa-pencil"></i>&nbsp;</a>

            <a id="link2Genre"
               class="btn btn-inverse"
               style="position:absolute;top:10px;right:80px"
               href="<c:url value='/documentaireByGenre.htm?genre=${docuDetail.genreId}'/>">
                <i class="fa fa-list"></i>&nbsp;</a>

        </div>

        </div>
    </div>
</section>

<%@ include file="modal-documentaire-edit.jsp"%>

</body>

