SELECT tin, taxpayer.id AS tpId, business_unit.id AS branchId, business_unit.name, business_unit.number_of_employees, business_unit.registration_date, business_unit.trade_name
FROM app.taxpayer.business_unit
         INNER JOIN app.taxpayer.business ON app.taxpayer.business_unit.business_id = app.taxpayer.business.id
         INNER JOIN app.taxpayer.taxpayer ON app.taxpayer.business.taxpayer_id = app.taxpayer.taxpayer.id
ORDER BY tin;

SELECT *
FROM app.taxpayer.taxpayer
         FULL OUTER JOIN app.taxpayer.business ON app.taxpayer.business.taxpayer_id = app.taxpayer.taxpayer.id
         FULL OUTER JOIN app.taxpayer.business_unit ON app.taxpayer.business_unit.business_id = app.taxpayer.business.id
WHERE business_unit.id is null
AND business.id is not null
ORDER BY tin
