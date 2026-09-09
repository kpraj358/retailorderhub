# RetailOrderHub — Day 2 Model Copy

System Design Training Program case study. This is the **facilitator/answer-key
state of the project as of the end of Day 2** — after the SRP→DIP refactor
(Lab 1) and the OCP Strategy Pattern exercise (Lab 2). It does **not** include
Lab 3's DebitCardStrategy — that's the change participants make themselves
during the GitHub Git Workflow lab, via their own branch and PR.

## What changed since Day 1

- `OrderManager` (the Day 1 God Object) is gone. It's now three focused
  collaborators:
  - `service/OrderService.java` — orchestration only (validate → check stock
    → charge → persist → update stock)
  - `service/InventoryService.java` — stock checks and updates
  - `service/PaymentService.java` — payment, now via the Strategy pattern
    instead of an if/else chain
- `validateCustomer()` / `validateItems()` were deleted — they duplicated
  inline checks and were never called (Day 1 Lab 3's Duplicated Code finding).
- `InventoryService` depends on `repository/InventoryRepository.java` (an
  interface), not on `EntityManager` directly (DIP). The only implementation
  today is `JpaInventoryRepository`.
- Payment methods are now individual `PaymentStrategy` classes
  (`CreditCardStrategy`, `PayPalStrategy`, `GiftCardStrategy`,
  `ApplePayStrategy`), auto-collected by Spring into a `Map<String,
  PaymentStrategy>`. Adding a payment method no longer touches
  `PaymentService.java` at all.

## What's deliberately NOT fixed

Day 2 is a SOLID exercise, not a security pass. Two Day 1 findings are
**intentionally still present** so they stay valid discussion points:

- **SQL injection** in `JpaInventoryRepository` — both native queries are
  still built by string concatenation.
- **TOCTOU race condition** in `OrderService.processOrder()` — stock is
  checked in one loop and decremented in a separate later loop, with payment
  in between.

If you're using this as an answer key, don't "helpfully" patch these without
flagging it to participants — the point is that a SOLID refactor doesn't
automatically fix structural/security issues.

## Running it

```
mvn spring-boot:run
```

Then open `http://localhost:8080`. Sample inventory (`widget`, `gadget`,
`gizmo`) is seeded on startup via `data.sql` — `gizmo` is seeded at 0 quantity
so you can test the out-of-stock path. The H2 console is available at
`http://localhost:8080/h2-console` (JDBC URL `jdbc:h2:mem:retailorderhub`).

## Lab-by-lab map

| Lab | What it does to this code |
| --- | --- |
| Day 2 Demo | Extracts `PaymentService` from `OrderManager` (if/else version) |
| Lab 1 | Extracts `InventoryService` + `OrderService`, renames `OrderManager`, applies DIP to `InventoryService` |
| Lab 2 | Rewrites `PaymentService` to the Strategy pattern, adds `ApplePayStrategy` |
| Lab 3 | Participant's own repo — adds `DebitCardStrategy` via branch → commit → push → PR → merge |
