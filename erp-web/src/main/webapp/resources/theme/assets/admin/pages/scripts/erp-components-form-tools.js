var ComponentsFormTools = function () {
	
    var handleBootstrapTouchSpin = function() {
        $(".touchspin").TouchSpin({          
            buttondown_class: 'btn blue',
            buttonup_class: 'btn blue',
            min: 0,
            max: 100000,
            stepinterval: 50,
            maxboostedstep: 100000
            //prefix: '$'
        }); 
    };
    
    var handleInputMasks = function () {
    	
        Inputmask.extendDefaults({
            'autoUnmask': true,
            'removeMaskOnSubmit': true,
            'showMaskOnHover': false, 
            'showMaskOnFocus': false
            });

        Inputmask.extendAliases({
            'telefoneMask': {
                mask: ['(99) 9999-9999', '(99) 99999-9999']
            },
            
            'percent': {
                prefix: "% ",
                groupSeparator: ",",
                alias: "numeric",
                placeholder: "0",
                autoGroup: !0,
                digits: 2,
                rightAlign: false,
                digitsOptional: !1,
                removeMaskOnSubmit: true
            },
            'decimalQnt': {
                groupSeparator: ",",
                alias: "numeric",
                placeholder: "0",
                digits: 2,
                rightAlign: false,
                digitsOptional: !1,
                removeMaskOnSubmit: true,
                autoUnmask: true
            },
            
            'currencyBR': {
                prefix: "R$ ",
                groupSeparator: ",",
                alias: "numeric",
                placeholder: "0",
                autoGroup: !0,
                digits: 2,
                rightAlign: false,
                digitsOptional: !1,
                removeMaskOnSubmit: true,
                autoUnmask: true
            }
        });
        
       $(":input").inputmask(); 
       $(".maskMoney").maskMoney({prefix:'R$ ', allowNegative: true, thousands:'.', decimal:',', affixesStay: false});
       $(".maskPercent").maskMoney({suffix:' %', allowNegative: true, thousands:'.', decimal:',', affixesStay: false});
       $('.uppercase').keyup(function() {this.value = this.value.toUpperCase();});
       $('.noespace').keyup(function(e) {
    	   	if (e.keyCode == 32){
    	   		$(this).val($(this).val().replace(/\s/g,''));
    	   	}
       });
       
       //init maxlength handler
       $('.maxlength-handler').maxlength({
           limitReachedClass: "label label-danger",
           alwaysShow: true,
           threshold: 5
       });
    }

    return {
        //main function to initiate the module
    	handleInputMasks: function () {
            handleInputMasks();
        },
        handleBootstrapTouchSpin: function () {
        	handleBootstrapTouchSpin();
        }
    };

}();