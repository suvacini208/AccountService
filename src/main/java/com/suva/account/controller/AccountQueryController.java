package com.suva.account.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.suva.account.model.AccountBalance;
import com.suva.account.model.AccountTransaction;
import com.suva.account.model.AccountTxnRequest;
import com.suva.account.service.AccountService;

@RestController
public class AccountQueryController {

	@Autowired
	AccountService accountBalanceService;

	@GetMapping("/balance/{accountNumber}")
	public AccountBalance getAccountBalance(@PathVariable String accountNumber) {
		return accountBalanceService.getAccountBalance(accountNumber);
	}

	@GetMapping("/transaction")
	public ResponseEntity<PagedModel<EntityModel<AccountTransaction>>> getAccountTxn(
			@RequestBody AccountTxnRequest acctTxnRequest, Pageable pageable,
			PagedResourcesAssembler<AccountTransaction> assembler) {
		Page<AccountTransaction> acctTxns = accountBalanceService.getAccountTransaction(acctTxnRequest, pageable);
		PagedModel<EntityModel<AccountTransaction>> pr = assembler.toModel(acctTxns,
				linkTo(AccountQueryController.class).slash("/transaction").withSelfRel());
		HttpHeaders responseHeader = new HttpHeaders();
		responseHeader.add("link", createLinkHeader(pr));
		return new ResponseEntity<>(
				assembler.toModel(acctTxns, linkTo(AccountQueryController.class).slash("/transaction").withSelfRel()),
				responseHeader, HttpStatus.OK);
	}

	private String createLinkHeader(PagedModel<EntityModel<AccountTransaction>> pr) {
		final StringBuilder linkHeader = new StringBuilder();
		/*linkHeader.append(buildLinkHeader(pr.getLinks("first").get(0).getHref(), "first"));
		linkHeader.append(", ");
		linkHeader.append(buildLinkHeader(pr.getLinks("next").get(0).getHref(), "next"));*/
		return linkHeader.toString();
	}

	public static String buildLinkHeader(final String uri, final String rel) {
		return "<" + uri + ">; rel=\"" + rel + "\"";
	}

}
