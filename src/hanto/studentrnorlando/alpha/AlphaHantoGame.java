/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Copyright ©2015 Gary F. Pollice
 *******************************************************************************/

package hanto.studentrnorlando.alpha;

import hanto.common.*;
import hanto.studentrnorlando.common.*;
import hanto.tournament.HantoMoveRecord;

import static hanto.common.MoveResult.*;
import static hanto.common.HantoPieceType.*;
import static hanto.common.HantoPlayerColor.*;
/**
 * The implementation of Alpha Hanto.
 * @version Mar 2, 2016
 */
public class AlphaHantoGame implements HantoGame
{
	private boolean firstMove = true;
	private HantoCordinateImpl blueButterflyHex = null, redButterflyHex = null;
	private final HantoPiece blueButterfly = new HantoPieceImpl(BLACK, BUTTERFLY);
	private final HantoPiece redButterfly = new HantoPieceImpl(WHITE, BUTTERFLY);
	private boolean gameOver= false;
	/*
	 * @see hanto.common.HantoGame#makeMove(hanto.common.HantoPieceType, hanto.common.HantoCoordinate, hanto.common.HantoCoordinate)
	 */
	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate source,
			HantoCoordinate destination) throws HantoException
	{		
		if (gameOver) {
			throw new HantoException("You cannot move after the game is finished");
		}
		if (pieceType != BUTTERFLY) {
			throw new HantoException("Only Butterflies are valid in Alpha Hanto");
		}
		
		final HantoCordinateImpl to = new HantoCordinateImpl(destination);
		
		if (firstMove) {
			if (to.getX() != 0 || to.getY() != 0) {
				throw new HantoException("Blue did not move Butterfly to origin");
			}
			blueButterflyHex = to;
		} else {
			if (!hexIsValidForRed(to)) {
				throw new HantoException("Red cannot place a piece in that hex");
			}
			redButterflyHex = to;
			gameOver = true;
		}
		
		final MoveResult moveResult = firstMove ? OK : DRAW;
		firstMove = false;
		return moveResult;
	}

	/**
	 * Check to make sure that the hex is valid for the Red player
	 * @param coordinate the coordinate to check
	 * @return true if the coordinate is valid for Red
	 */
	private boolean hexIsValidForRed(HantoCordinateImpl coordinate)
	{
		return (coordinate.equals(new HantoCordinateImpl(0, 1))
				|| coordinate.equals(new HantoCordinateImpl(1, 0))
				|| coordinate.equals(new HantoCordinateImpl(1, -1))
				|| coordinate.equals(new HantoCordinateImpl(0, -1))
				|| coordinate.equals(new HantoCordinateImpl(-1, 0))
				|| coordinate.equals(new HantoCordinateImpl(-1, 1)));
	}

	/*
	 * @see hanto.common.HantoGame#getPieceAt(hanto.common.HantoCoordinate)
	 */
	@Override
	public HantoPiece getPieceAt(HantoCoordinate coordinate)
	{
		final HantoCordinateImpl where = new HantoCordinateImpl(coordinate);
		return where.equals(blueButterflyHex) ? blueButterfly
				: where.equals(redButterflyHex) ? redButterfly
				: null;
	}

	/*
	 * @see hanto.common.HantoGame#getPrintableBoard()
	 */
	@Override
	public String getPrintableBoard()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MoveResult makeMove(HantoMoveRecord record) throws HantoException {
		return this.makeMove(record.getPiece(), record.getFrom(), record.getTo());
	}

}
