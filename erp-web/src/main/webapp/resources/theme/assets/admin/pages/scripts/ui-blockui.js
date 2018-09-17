var UIBlockUI = function() {

    return {
        //main function to initiate the module
    	block: function() {
    		Metronic.blockUI({
                target: '.blockui_portlet_body',
                animate: true
            });
        },
    	unblock: function() {
    		 Metronic.unblockUI('.blockui_portlet_body');
        },

    };

}();