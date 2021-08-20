package com.shegoestech.foodie.service;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.shegoestech.foodie.models.IngredientAmounts;
import com.shegoestech.foodie.models.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor

public class ShoppingListExporter {

    private final List<Recipe> menu;


    private void writeTableHeader(PdfPTable table) {

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.gray);
        cell.setPadding(10);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Product", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Amount", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Measure", font));
        table.addCell(cell);
    }

    public void writeTableData(PdfPTable table) {
        Map<String, Long> products = new HashMap<>();
        List<String> productList = new ArrayList<>();

        for (Recipe recipe : menu) {
            for (IngredientAmounts ing : recipe.getIngredientAmounts()) {
                String name = ing.getIngredient().getIngredientName();
                long amount = ing.getAmount();
                productList.add(name);

                if (products.containsKey(name)) {
                    amount += products.get(name);
                    table.deleteRow(productList.indexOf(name) + 1);
                    productList.remove(name);
                }
                products.put(name, amount);
                table.addCell(name);
                table.addCell(String.valueOf(amount));
                String measure = ing.getIngredient().getIngredientMeasure();
                table.addCell(measure);
            }
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Paragraph title = new Paragraph("Grab a wallet and head to the shop to get these yummy products");
        title.setAlignment(Paragraph.ALIGN_CENTER);
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.gray);
        font.setSize(30);

        document.add(title);
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);
        table.setWidths(new float[]{3.0f, 3.0f, 3.0f});

        writeTableHeader(table);
        writeTableData(table);
        document.add(table);
        document.close();
    }
}
