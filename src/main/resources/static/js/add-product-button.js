$(document).ready(function(){

var html = '<tr><td class="col-sm-4"><input type="text" class="form-control" placeholder="Product" aria-label="Product" name="ingredient[]" required list="ingredients" autocomplete="off" id="ingredientInput"/>' +
    '<datalist id="ingredients"><option th:each="i : ${ingredients}" th:text="${i.ingredientName}"></option></datalist></td>' +
    '<td class="col-sm-2"><input type="number" class="form-control" placeholder="Amount" name="amount[]" aria-label="Amount" required></td><td class="col-sm-2">' +
    '<input type="text" class="form-control measure" placeholder="Measure" aria-label="Measure" id="units" disabled></td>' +
    '<td class="col-sm-2"><button type="button" class="btn btn-danger" name="remove" id="remove" value="remove">Remove</button></td></tr>'




      $("#input_field").on('click', '#remove', function(){
                     $(this).closest('tr').remove();
        });


      $("#add").on('click', function(){
      i++;
     $("#input_field").append(html);
        });


        $('tbody').on('change', '#ingredientInput', function(){
          var x = document.getElementById("ingredientInput").value;
//          document.getElementById("units").value = "gr";
          $('#units').attr('placeholder', x);

        })


//
//document.getElementById("ingredientInput").addEventListener("change", function(){
//  var x = document.getElementById("ingredientInput").value;
//  document.getElementById("units").value = x;
//});



});








