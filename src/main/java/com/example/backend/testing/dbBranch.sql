SELECT tin, taxpayer.id AS tpId, business_unit.id AS branchId, business_unit.name, business_unit.number_of_employees, business_unit.registration_date, business_unit.trade_name
FROM app.taxpayer.business_unit
         INNER JOIN app.taxpayer.business ON app.taxpayer.business_unit.business_id = app.taxpayer.business.id
         INNER JOIN app.taxpayer.taxpayer ON app.taxpayer.business.taxpayer_id = app.taxpayer.taxpayer.id
ORDER BY tin