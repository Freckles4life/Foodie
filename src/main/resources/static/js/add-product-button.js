$(document).ready(function(){

var html = '<tr><td class="col-sm-4"><input type="text" class="form-control product" placeholder="Product" aria-label="Product" name="ingredient[]" required list="ingredients" autocomplete="off"/>' +
    '<datalist id="ingredients"><option th:each="i : ${ingredients}" th:text="${i.ingredientName}"></option></datalist></td>' +
    '<td class="col-sm-2"><input type="number" class="form-control" placeholder="Amount" name="amount[]" aria-label="Amount" required></td><td class="col-sm-2">' +
    '<input type="text" class="form-control measure" placeholder="Measure" aria-label="Measure" disabled></td>' +
    '<td class="col-sm-2"><button type="button" class="btn btn-danger" name="remove" id="remove" value="remove">Remove</button></td></tr>'


var i = 1;


      $("#input_field").on('click', '#remove', function(){
                     $(this).closest('tr').remove();
        });


        $('form').on('click', '#add', function(){
        i++;
           $("#input_field").append(html);
           $(".product").last().attr("id", "ingredientInput" + i);
                $(".measure").last().attr("id", "units" + i);
        })


// code for insering ml, gr, pieces in fields

        $('tbody').on('change', '#ingredientInput1', function(){
        // x should be changed to respective unit for the product
          var x = document.getElementById("ingredientInput1").value;
              $('#units1').val(x);
        })

        $('tbody').on('change', '#ingredientInput2', function(){
          var x = document.getElementById("ingredientInput2").value;
              $('#units2').val(x);
        })

        $('tbody').on('change', '#ingredientInput3', function(){
          var x = document.getElementById("ingredientInput3").value;
              $('#units3').val(x);
        })





});








