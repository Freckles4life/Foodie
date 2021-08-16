$(document).ready(function(){

var html = '<tr><td class="col-sm-4"><input type="text" class="form-control" placeholder="Product" aria-label="Product" name="ingredient"list="ingredients" autocomplete="off"/><datalist id="ingredients"><option th:each="i : ${ingredients}" th:text="${i.ingredientName}"></option></datalist></td><td class="col-sm-2"><input type="text" class="form-control" placeholder="Amount" aria-label="Amount"></td><td class="col-sm-2"><input type="text" class="form-control" placeholder="Measure" aria-label="Measure" disabled></td><td class="col-sm-2"><button type="button" class="btn btn-danger" name="remove" id="remove" value="remove">Remove</button></td></tr>'

    $("#add").click(function(){
        $("#input_field").append(html);
    });

      $("#input_field").on('click', '#remove', function(){
                     $(this).closest('tr').remove();

        });



});


