jQuery.validator.addMethod("cnpj", function (cnpj, element) {
    cnpj = jQuery.trim(cnpj);

    // DEIXA APENAS OS NÚMEROS
    cnpj = cnpj.replace('/', '');
    cnpj = cnpj.replace('.', '');
    cnpj = cnpj.replace('.', '');
    cnpj = cnpj.replace('-', '');

    var numeros, digitos, soma, i, resultado, pos, tamanho, digitos_iguais;
    digitos_iguais = 1;

    if (cnpj.length < 14 && cnpj.length < 15) {
        return this.optional(element) || false;
    }
    for (i = 0; i < cnpj.length - 1; i++) {
        if (cnpj.charAt(i) != cnpj.charAt(i + 1)) {
            digitos_iguais = 0;
            break;
        }
    }

    if (!digitos_iguais) {
        tamanho = cnpj.length - 2
        numeros = cnpj.substring(0, tamanho);
        digitos = cnpj.substring(tamanho);
        soma = 0;
        pos = tamanho - 7;

        for (i = tamanho; i >= 1; i--) {
            soma += numeros.charAt(tamanho - i) * pos--;
            if (pos < 2) {
                pos = 9;
            }
        }
        resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
        if (resultado != digitos.charAt(0)) {
            return this.optional(element) || false;
        }
        tamanho = tamanho + 1;
        numeros = cnpj.substring(0, tamanho);
        soma = 0;
        pos = tamanho - 7;
        for (i = tamanho; i >= 1; i--) {
            soma += numeros.charAt(tamanho - i) * pos--;
            if (pos < 2) {
                pos = 9;
            }
        }
        resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
        if (resultado != digitos.charAt(1)) {
            return this.optional(element) || false;
        }
        return this.optional(element) || true;
    } else {
        return this.optional(element) || false;
    }
}, "Digite um CNPJ válido.");

jQuery.validator.addMethod("cpf", function (value, element) {
    value = jQuery.trim(value);

    value = value.replace('.', '');
    value = value.replace('.', '');
    cpf = value.replace('-', '');
    while (cpf.length < 11) cpf = "0" + cpf;
    var expReg = /^0+$|^1+$|^2+$|^3+$|^4+$|^5+$|^6+$|^7+$|^8+$|^9+$/;
    var a = [];
    var b = new Number;
    var c = 11;
    for (i = 0; i < 11; i++) {
        a[i] = cpf.charAt(i);
        if (i < 9) b += (a[i] * --c);
    }
    if ((x = b % 11) < 2) { a[9] = 0 } else { a[9] = 11 - x }
    b = 0;
    c = 11;
    for (y = 0; y < 10; y++) b += (a[y] * c--);
    if ((x = b % 11) < 2) { a[10] = 0; } else { a[10] = 11 - x; }
    if ((cpf.charAt(9) != a[9]) || (cpf.charAt(10) != a[10]) || cpf.match(expReg)) return this.optional(element) || false;
    return this.optional(element) || true;
}, "Digite um CPF válido."); 

jQuery.validator.addMethod("dateBR", function (value, element) {
    //contando chars    
    if (value.length != 10) return (this.optional(element) || false);
    // verificando data
    var data = value;
    var dia = data.substr(0, 2);
    var barra1 = data.substr(2, 1);
    var mes = data.substr(3, 2);
    var barra2 = data.substr(5, 1);
    var ano = data.substr(6, 4);
    if (data.length != 10 || barra1 != "/" || barra2 != "/" || isNaN(dia) || isNaN(mes) || isNaN(ano) || dia > 31 || mes > 12) return (this.optional(element) || false);
    if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia == 31) return (this.optional(element) || false);
    if (mes == 2 && (dia > 29 || (dia == 29 && ano % 4 != 0))) return (this.optional(element) || false);
    if (ano < 1900) return (this.optional(element) || false);
    return (this.optional(element) || true);
}, "Digite uma data válida");  

jQuery.validator.addMethod("notequal", function (value, element, param) {
    return this.optional(element) || (value == $(param).val() ? false : true);
}, "Este valor não pode ser igual");


jQuery.validator.addMethod("telefone", function (value, element) {
	value = value.replace("(", "");
	value = value.replace(")", "");
	value = value.replace("-", "");
	value = value.replace("_", "");
	value = value.replace(" ", "");
	return this.optional(element) || /[0-9]{10}/.test(value) || /[0-9]{11}/.test(value);

}, "Digite um telefone válido");

jQuery.validator.addMethod("celular", function (value, element) {
	value = value.replace("(", "");
	value = value.replace(")", "");
	value = value.replace("-", "");
	value = value.replace("_", "");
	value = value.replace(" ", "");
	return this.optional(element) || /[0-9]{10}/.test(value) || /[0-9]{11}/.test(value);
}, "Digite um celular válido.");

jQuery.validator.addMethod("cep",function(e,t){return this.optional(t)||/^\d{5}-\d{3}?$/.test(e)
},"Digite um CEP válido.");

var FormValidation = function () {

    // basic validation
    var handleValidation1 = function(form1) {
        // for more info visit the official plugin documentation: 
            // http://docs.jquery.com/Plugins/Validation

            var error1 = $('.alert-danger', form1);
            var success1 = $('.alert-success', form1);

            form1.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block help-block-error', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",  // validate all fields including form hidden input
                messages: {
                    select_multi: {
                        maxlength: jQuery.validator.format("Max {0} items allowed for selection"),
                        minlength: jQuery.validator.format("At least {0} items must be selected")
                    }
                },
                rules: {
                	//cadastro produto
                	select_vendavel: {
                        required: true
                    },
                	codProduto: {
                        required: true
                    },
                    nomeProduto: {
                        required: true
                    },
                    selectControlaEstoque: {
                        required: true
                    },
                    undMedidaProduto: {
                        required: true
                    },    
                    precoFinalProduto: {
                        required: true
                    },    
                    qntdeUsada: {
                    	required: function(element) {
                            return  $("#selectControlaEstoque").val()!='NAO_CONTROLA_ESTOQUE';
                          }
                    }, 
                    qntdeAtual: {
                    	required: function(element) {
                            return  $("#selectControlaEstoque").val()!='NAO_CONTROLA_ESTOQUE';
                          }
                    }, 
                	//cadastro cliente
                	nome: {
                        minlength: 2,
                        required: true
                    },
                    cpf: {
                        required: false,
                        cpf: true
                    },
                    cnpj: {
                        required: false,
                        cnpj: true
                    },
                    email: {
                        required: false,
                        email: true
                    },
                    emailSec: {
                        required: false,
                        email: true
                    },
                    dtNasc: {
                        required: false,
                        dateBR: true
                    },
                    dtEmissao: {
                        required: false,
                        dateBR: true
                    },
                    Residencial: {
                        required: false,
                        telefone: true
                    },
                    Celular: {
                        required: false,
                        celular: true
                    },
                    url: {
                        required: false,
                        url: true
                    },
                    number: {
                        required: true,
                        number: true
                    },
                    digits: {
                        required: true,
                        digits: true
                    },
                    creditcard: {
                        required: true,
                        creditcard: true
                    },
                    occupation: {
                        minlength: 5,
                    },
                    select: {
                        required: true
                    },
                    select_multi: {
                        required: true,
                        minlength: 1
                    }
                },

                invalidHandler: function (event, validator) { //display error alert on form submit              
                    success1.hide();
                    error1.show();
                    Metronic.scrollTo(error1, -200);
                },

                highlight: function (element) { // hightlight error inputs
                    $(element)
                        .closest('.form-group').addClass('has-error'); // set error class to the control group
                },

                unhighlight: function (element) { // revert the change done by hightlight
                    $(element)
                        .closest('.form-group').removeClass('has-error'); // set error class to the control group
                },

                success: function (label) {
                    label
                        .closest('.form-group').removeClass('has-error'); // set success class to the control group
                },

                submitHandler: function (form) {
                    success1.show();
                    error1.hide();
                    ComponentsFormTools.init();
                    form[0].submit(); // submit the form
                }
            });
            
    }
    
    return {
        //main function to initiate the module
        init: function (form1) {
            handleValidation1(form1);
        }

    };

}();

jQuery.extend(jQuery.validator.messages, {
    required: "Campo obrigatório.",
    remote: "Please fix this field.",
    email: "Digite um e-mail válido",
    url: "Please enter a valid URL.",
    date: "Please enter a valid date.",
    dateISO: "Please enter a valid date (ISO).",
    number: "Please enter a valid number.",
    digits: "Please enter only digits.",
    creditcard: "Please enter a valid credit card number.",
    equalTo: "Please enter the same value again.",
    accept: "Please enter a value with a valid extension.",
    maxlength: jQuery.validator.format("Please enter no more than {0} characters."),
    minlength: jQuery.validator.format("Digite pelo menos {0} caracteres."),
    rangelength: jQuery.validator.format("Please enter a value between {0} and {1} characters long."),
    range: jQuery.validator.format("Please enter a value between {0} and {1}."),
    max: jQuery.validator.format("Please enter a value less than or equal to {0}."),
    min: jQuery.validator.format("Please enter a value greater than or equal to {0}.")
});