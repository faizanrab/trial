package in.ecgc.erp.pebPolicy.exceptions;

import javax.validation.GroupSequence;

@GroupSequence({ ProposalBasicInfo.class, ProposalScrutinyInfo.class })
public interface ValidationChecks {

}
