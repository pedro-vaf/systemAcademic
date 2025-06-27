package logic;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;
import event.BaseEvent;
import participant.BaseParticipant;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;
import java.util.Scanner;

public class CertificateLogic {
    public static void certificateLogic() {
        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.println("==========================================");
        System.out.println("            PARTICIPANT LIST              ");
        System.out.println("==========================================");

        for (BaseParticipant participant : BaseParticipant.getListParticipant()) {
            System.out.printf("ID: %-5d | Name: %s%n", participant.getIdParticipant(), participant.getName());
        }

        System.out.println("------------------------------------------");
        System.out.print("Enter participant ID: ");
        int idSelected = sc.nextInt();

        BaseParticipant selectedParticipant = null;
        for (BaseParticipant p : BaseParticipant.getListParticipant()) {
            if (p.getIdParticipant() == idSelected) {
                selectedParticipant = p;
                break;
            }
        }

        if (selectedParticipant == null) {
            System.out.println("\n[ERROR] Participant not found.");
            return;
        }

        Set<Integer> eventIds = ParticipantEventLinkLogic.getAllLinks().get(idSelected);
        if (eventIds == null || eventIds.isEmpty()) {
            System.out.println("\n[INFO] This participant is not registered in any events.");
            return;
        }

        // PDF generation
        try {
            Document document = new Document(PageSize.A4);
            String folder = "output";
            new File(folder).mkdirs(); // cria a pasta se não existir
            String fileName = folder + "/certificate_" + selectedParticipant.getIdParticipant() + ".pdf";

            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            // Fonte personalizada
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, Color.BLUE);
            Font sectionTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
            Font normal = FontFactory.getFont(FontFactory.HELVETICA, 12);

            // Título centralizado
            Paragraph title = new Paragraph("Certificate of Participation", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" ")); // espaço

            // Linha separadora
            document.add(new LineSeparator());

            // Dados do participante
            document.add(new Paragraph("Participant Information", sectionTitle));
            document.add(new Paragraph("Name : " + selectedParticipant.getName(), normal));
            document.add(new Paragraph("Age  : " + selectedParticipant.getAge(), normal));
            document.add(new Paragraph("Email: " + selectedParticipant.getEmail(), normal));

            document.add(new Paragraph(" "));
            document.add(new LineSeparator());
            document.add(new Paragraph(" "));

            // Eventos
            document.add(new Paragraph("Registered Events", sectionTitle));
            for (BaseEvent event : BaseEvent.getListEvent()) {
                if (eventIds.contains(event.getIdEvent())) {
                    document.add(new Paragraph("• " + event.getTitle(), normal));
                    document.add(new Paragraph("   Date       : " + event.date, normal));
                    document.add(new Paragraph("   Address    : " + event.getAddress(), normal));
                    document.add(new Paragraph("   Description: " + event.getDescription(), normal));
                    document.add(new Paragraph(" "));
                }
            }
            
            document.close();
            System.out.println("\n[INFO] PDF gerado com sucesso em: " + new File(fileName).getAbsolutePath());

        } catch (DocumentException | IOException e) {
            System.out.println("\n[ERROR] Falha ao gerar o PDF.");
            e.printStackTrace();
        }
    }
}