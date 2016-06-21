package fr.clunven.docu.web.controller;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.clunven.docu.dao.db.dto.DocumentaireDetail;
import fr.clunven.docu.utils.ImageUtils;
import fr.clunven.docu.web.domain.DocuConstants;


/**
 * Controller permettant la mise à jour du documentaire dans la base
 */
@Controller("controller." + DocuConstants.VIEW_DOCUMENTAIRE_UPDATE)
@RequestMapping("/" + DocuConstants.VIEW_DOCUMENTAIRE_UPDATE + ".htm")
public class DocumentaireUpdateController extends BaseController {

    /**
     * En sortie d'update on souhaite afficher la liste des documentaires de meme genre
     */
    public DocumentaireUpdateController() {
        setSuccessView(VIEW_DOCUMENTAIRE_GEN);
        setCancelView(VIEW_DOCUMENTAIRE_GEN);
    }

    /**
     * Allows to display screen.
     *
     * @return model and viewm
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showPage(HttpServletRequest req) throws Exception {
        ModelAndView mav = renderPage(req);
        mav.addObject(BEAN_MENU, menuService.getMenuDocumentairesByGenre());
        mav.addObject(BEAN_GENRE, referentialDbDao.getGenreById(23));
        mav.addObject(BEAN_DOCUBYGENRE, menuService.getByGenre(23));
        return mav;
    }

    /**
     * Analyse du formulaire
     * @return Model
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView updateDocumentaire(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // Object used to update the documentaire object
        DocumentaireDetail dd = new DocumentaireDetail();

        // Element retourné
        ModelAndView mav = renderPage(req);

        if (ServletFileUpload.isMultipartContent(req)) {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
            for (FileItem item : items) {

                // Enforce UTF-8 (to write to DB - cannot convert)
                byte itemIso[]  = item.getString().getBytes(ISO_8859_1);
                String itemUtf8 = new String(itemIso, UTF_8);

                if (!item.isFormField() && "coverfile".equals(item.getFieldName())) {
                    String coverfile = FilenameUtils.getName(item.getName());
                    log.info("Cover:" + coverfile);

                    ImageUtils.ImageType coverImageFormat = ImageUtils.ImageType.png;
                    if (coverfile.toLowerCase().endsWith(ImageUtils.ImageType.jpg.toString())) {
                        coverImageFormat = ImageUtils.ImageType.jpg;
                    } else if (coverfile.toLowerCase().endsWith(ImageUtils.ImageType.jpeg.toString())) {
                        coverImageFormat = ImageUtils.ImageType.jpeg;
                    }
                    log.info("Format:" + coverImageFormat);

                    if (item.getInputStream().available() > 0) {
                        dd.setImage(ImageUtils.fromFileToBase64(item.getInputStream(), coverImageFormat));
                    }

                } else if ("genre".equals(item.getFieldName()) && !"".equals(item.getString())) {
                    int genreInt = new Integer(item.getString());
                    dd.setGenreId(genreInt);
                    mav.addObject(BEAN_GENRE,       referentialDbDao.getGenreById(genreInt));
                    mav.addObject(BEAN_MENU,        menuService.getMenuDocumentairesByGenre());
                    mav.addObject(BEAN_DOCUBYGENRE, menuService.getByGenre(genreInt));

                } else if ("docuNote".equals(item.getFieldName()) && (!"".equals(item.getString()))) {
                    dd.setNote(new Integer(item.getString()));

                } else if ("quality".equals(item.getFieldName()) && (!"".equals(item.getString()))) {
                    dd.setQualite(new Integer(item.getString()));

                } else if ("vu".equals(item.getFieldName())) {
                    dd.setVu(item.getString().equals("OUI"));

                } else if ("uid".equals(item.getFieldName())) {
                    dd.setId(new Integer(item.getString()));

                } else if ("genre".equals(item.getFieldName())) {
                    dd.setTitre(itemUtf8);

                } else if ("titreOriginal".equals(item.getFieldName())) {
                    dd.setTitreOriginal(itemUtf8);

                } else if ("titre".equals(item.getFieldName())) {
                    dd.setTitre(itemUtf8);

                }else if ("docuPays".equals(item.getFieldName()) && (!"".equals(item.getString()))) {
                    dd.setPays(itemUtf8);

                } else if ("duree".equals(item.getFieldName()) && (!"".equals(item.getString()))) {
                    dd.setDuree(new Integer(item.getString()));

                } else if ("annee".equals(item.getFieldName()) && (!"".equals(item.getString()))) {
                    dd.setAnnee(new Integer(item.getString()));

                } else if ("description".equals(item.getFieldName())) {
                    dd.setDescription(itemUtf8);

                } else if ("docuLangue2".equals(item.getFieldName()) && (!"".equals(item.getString()))) {
                    dd.setLangue(itemUtf8);

                } else if ("docuSousTitre2".equals(item.getFieldName()) && (!"".equals(item.getString()))) {
                    dd.setSoustitre(item.getString());

                } else if ("docuFormat2".equals(item.getFieldName())&& (!"".equals(item.getString()))) {
                    dd.setFormat(item.getString());

                } else if ("resolution".equals(item.getFieldName())) {
                    dd.setResolution(item.getString());

                } else if ("taille".equals(item.getFieldName()) && (!"".equals(item.getString()))) {
                    dd.setTaille(new Integer(item.getString()));
                }
            }
        }

        log.info("Update Documentaire - " + dd.getTitre() + "(" + dd.getImage() + ")");
        if (dd.getId() > 0) {
            documentaryDbDao.update(dd);
            saveMessage(req, "Account has been successfully updated");
        } else {
            saveError(req, "Impossible de mettre a jour le documentaire - non trouvé " + dd.getId());
        }

        return mav;
    }

}
