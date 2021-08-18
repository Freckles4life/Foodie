package com.shegoestech.foodie.service;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import com.shegoestech.foodie.controller.RecipeController;
import com.shegoestech.foodie.models.Ingredient;
import com.shegoestech.foodie.models.IngredientAmounts;
import com.shegoestech.foodie.models.Recipe;
import com.shegoestech.foodie.repository.IngredientAmountsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ShoppingListExporter {

    private final List<Recipe> menu;
    

    private void writeTableHeader(PdfPTable table) {

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.GRAY);
        cell.setPadding(5);
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

        for (Recipe recipe : menu) {
            table.addCell(String.valueOf(recipe.getIngredientAmounts()));
            table.addCell(String.valueOf(recipe.getType()));
            table.addCell(String.valueOf(recipe.getRecipeInstructions()));
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Paragraph title = new Paragraph("Your shopping list for today");
        title.setAlignment(Paragraph.ALIGN_CENTER);
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.gray);
        font.setSize(18);

        document.add(title);
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);
        table.setWidths(new float[]{4.0f, 3.0f, 2.0f});

        writeTableHeader(table);
        writeTableData(table);
        document.add(table);
        document.close();
    }
}
