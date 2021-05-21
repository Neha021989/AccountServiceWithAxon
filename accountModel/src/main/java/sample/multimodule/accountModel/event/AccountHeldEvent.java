package sample.multimodule.accountModel.event;

import sample.multimodule.accountModel.entity.Status;

public class AccountHeldEvent extends BaseEvent<String> {

	public final Status status;

	public AccountHeldEvent(String id, Status status) {
		super(id);
		this.status = status;
	}

}
