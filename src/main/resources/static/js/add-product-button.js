let i = 2;
console.log(ingredients);

let map = {};
for (var j = 0; j < ingredients.length; j++) {
    var name = ingredients[j].ingredientName;
    map[name] = ingredients[j].ingredientMeasure;
}
console.log(map);

function fillMeasure(id, unitId) {
    var x = document.getElementById(id).value;
    $('#' + unitId + '').val(map[x]);
}

$(document).ready(function () {

    var html = '<tr><td class="col-sm-4">' +
        '<input type="text" class="form-control product" placeholder="Product" aria-label="Product" name="ingredient[]" required list="ingredients" autocomplete="off"/>' +
        '<datalist id="ingredients"><option th:each="i : ${ingredients}" th:text="${i.ingredientName}"></option></datalist></td>' +
        '<td class="col-sm-2"><input type="number" class="form-control" placeholder="Amount" name="amount[]" aria-label="Amount" required></td><td class="col-sm-2">' +
        '<input type="text" class="form-control measure" placeholder="Measure" aria-label="Measure" disabled></td>' +
        '<td class="col-sm-2"><button type="button" class="btn btn-danger" name="remove" id="remove" value="remove">Remove</button></td></tr>'

    $("#input_field").on('click', '#remove', function () {
        $(this).closest('tr').remove();
    });


    $('form').on('click', '#add', function () {
        i++;

        var id = "ingredientInput" + i;
        var unitId = "units" + i;
        $("#input_field").append(html);
        $(".product").last().attr("id", id);
        $(".measure").last().attr("id", unitId);
        var preview = document.getElementById(id); //getElementById instead of querySelectorAll
        preview.setAttribute("onchange", "fillMeasure('" + id + "','" + unitId + "')");
    })


// code for insering ml, gr, pieces in fields

    // $('tbody').on('change', '#ingredientInput1', function () {
    //     // x should be changed to respective unit for the product
    //     var x = document.getElementById("ingredientInput1").value;
    //     $('#units1').val(map[x]);
    // })
    //
    // $('tbody').on('change', '#ingredientInput2', function () {
    //     var x = document.getElementById("ingredientInput2").value;
    //     $('#units2').val(map[x]);
    // })
    //
    // $('tbody').on('change', '#ingredientInput3', function () {
    //     var x = document.getElementById("ingredientInput3").value;
    //     $('#units3').val(map[x]);
    // })


});








