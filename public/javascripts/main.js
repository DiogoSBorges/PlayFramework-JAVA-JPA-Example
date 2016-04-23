/**
 * Created by diogo on 01/04/16.
 */
$(function () {
    console.log('Init')
    $('#addProductSale').click(addProduct);

});

var productsAdd = [];

function addProduct() {


    var $idProduto = $("#productSelect").val();

    if (productsAdd.indexOf($idProduto) == -1) {
        productsAdd.push($idProduto);

        $.getJSON("/products/" + $idProduto, function (product) {
            var nameProduct = product.name;
            var description = product.description;
            var brand = product.brand;

            $("#example > tbody").append("<tr> " +
                "<input type='hidden' name='productsIds[]' value='" + $idProduto + "'/>" +
                "<th class='col-sm-2' style='vertical-align:middle;'>" + nameProduct + "</th> " +
                "<th class='col-sm-6' style='vertical-align :middle;'>" + description + "</th>" +
                "<th class='col-sm-2 text-center' style='vertical-align:middle;'>" + brand + "</th> " +
                "<th class='col-sm-2 text-center' style='vertical-align :middle;'>  " +
                "<button id='removeProductSale' type='button' class='btn btn-danger'>Remove</button>" +
                "</th> " +
                "</tr>");
            $("#productSelect").val(' ');
        });

    } else {
        alert("The product has already been added");
    }


}
/*
 *
 <tr>
 <th class="col-sm-2" style="vertical-align : middle ;" >
 <a href="/products/50"> Ball</a>
 </th>
 <th class="col-sm-6" style="vertical-align : middle ;" >
 Football
 </th>
 <th class="col-sm-2 text-center" style="vertical-align : middle ;" >
 Nike
 </th>
 <th class="col-sm-2 text-center" style="vertical-align : middle ;">


 <form action="/sales/50/addProduct/" method="POST" >

 <input type="submit" value="Remove" class="btn btn-danger">

 </form>

 </th>
 </tr>
 * */