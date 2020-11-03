/*To count letters in Remarks field of Basic Proposal Details tab*/
function basicProposalDetailsRemarksCountChar(val, maxchar) {
	var len = val.value.length;
	if (len >= maxchar) {
		val.value = val.value.substring(0, maxchar);
	} else {
		$('#remarksCharNumLeft').text(maxchar - len);
	}
	$('#remarksCharNum').text(len);
};

/*To count letters in Remarks field of Assessment tab*/
function assessmentRemarksCountChar(val, maxchar) {
	var len = val.value.length;
	if (len >= maxchar) {
		val.value = val.value.substring(0, maxchar);
	} else {
		$('#assessmentRemarksCharNumLeft').text(maxchar - len);
	}
	$('#assessmentRemarksCharNum').text(len);
};

/*To count letters in ECGC Recommendation field of Decision tab*/
function ecgcRecommendationCountChar(val, maxchar) {
	var len = val.value.length;
	if (len >= maxchar) {
		val.value = val.value.substring(0, maxchar);
	} else {
		$('#ecgcRecommendationCharNumLeft').text(maxchar - len);
	}
	$('#ecgcRecommendationCharNum').text(len);
};

/*To count letters in COD Recommendation field of Decision tab*/
function codRecommendationCountChar(val, maxchar) {
	var len = val.value.length;
	if (len >= maxchar) {
		val.value = val.value.substring(0, maxchar);
	} else {
		$('#codRecommendationCharNumLeft').text(maxchar - len);
	}
	$('#codRecommendationCharNum').text(len);
};

	/*To count digits in Contract Value field of Pre-Bid Quote*/
	function countContractDigit(val, maxchar) {
	    var len = val.value.length;
	    if (len >= maxchar) {
	      val.value = val.value.substring(0, maxchar);
	      alert("You can enter maximum 15 digits only!")
	    } else {
	      $('#contractDigitLeft').text(maxchar - len);
	    }
	    $('#contractDigit').text(len);
	  };
  
  /*To count digits in Supply Value field of Pre-Bid Quote*/
  function countSupplyDigit(val, maxchar) {
      var len = val.value.length;
      if (len >= maxchar) {
        val.value = val.value.substring(0, maxchar);
      } else {
        $('#supplyDigitLeft').text(maxchar - len);
      }
      $('#supplyDigit').text(len);
    };
    
    /*To count digits in Service Value field of Pre-Bid Quote*/
    function countServiceDigit(val, maxchar) {
        var len = val.value.length;
        if (len >= maxchar) {
          val.value = val.value.substring(0, maxchar);
        } else {
          $('#serviceDigitLeft').text(maxchar - len);
        }
        $('#serviceDigit').text(len);
      };
      
      /*To count letters in Project Details field of Pre-Bid Quote*/
      function projectDetailsCountChar(val, maxchar) {
      	var len = val.value.length;
      	if (len >= maxchar) {
      		val.value = val.value.substring(0, maxchar);
      	} else {
      		$('#projectDetailsCharNumLeft').text(maxchar - len);
      	}
      	$('#projectDetailsCharNum').text(len);
      };
      
      /*To count letters in Security Details field of Pre-Bid Quote*/
      function securityCountChar(val, maxchar) {
      	var len = val.value.length;
      	if (len >= maxchar) {
      		val.value = val.value.substring(0, maxchar);
      	} else {
      		$('#securityCharNumLeft').text(maxchar - len);
      	}
      	$('#securityCharNum').text(len);
      };
    